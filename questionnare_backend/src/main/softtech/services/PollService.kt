package main.softtech.services

import com.fasterxml.jackson.databind.ObjectMapper
import main.softtech.dao.BaseDao
import main.softtech.db_model.ConditionalQuestion
import main.softtech.db_model.NumberRange
import main.softtech.db_model.Option
import main.softtech.db_model.Question
import main.softtech.model.PollModel
import main.softtech.model.QuestionModel
import org.apache.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/poll")
class PollService {
  @Autowired
  lateinit var questionDao: BaseDao<Question>
  @Autowired
  lateinit var optionDao: BaseDao<Option>
  @Autowired
  lateinit var conditionalQuestionDao: BaseDao<ConditionalQuestion>
  @Autowired
  lateinit var numberRangeDao: BaseDao<NumberRange>

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getPoll(): Response {
    return try {
      val questions = questionDao.getAll()
      return Response.ok(ObjectMapper().writeValueAsString(questions)).build()
    } catch (e: Exception) {
      logger.error("Cannot create Poll.", e)
      Response.serverError().entity("Cannot create Poll. Error is [${e.message}]").build()
    }
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  fun savePoll(poll: PollModel): Response {
    return try {
      for (questionModel in poll.questions) {
        val question = questionModel.toQuestionDBModel()
        question.id = questionDao.save(question)
        processNumberRangeIfPossible(questionModel, question)
        logger.debug("[${question.questionText}] question is created.")

        //Process Options
        val options = questionModel.questionType.options?.map { optionText ->
          val option = Option(optionText, question)
          option.id = optionDao.save(option)
          logger.debug("[$option] option is created.")
          option
        }
        options?.let { processConditionalQuestions(questionModel, it, question) }
      }
      return Response.status(201).build()
    } catch (e: Exception) {
      logger.error("Cannot create Poll.", e)
      Response.serverError().entity("Cannot create Poll. Error is [${e.message}]").build()
    }
  }

  //TODO this method should be changed depending on how the other type of predicates and conditions are
  private fun processConditionalQuestions(questionModel: QuestionModel, options: List<Option>, question: Question) {
    if (questionModel.questionType.type == "single_choice_conditional") {
      // It is assumed that right now it only works with predicates
      // Design should be changed after different different inputs of the predicate
      try {
        val predicateObject = questionModel.questionType.condition?.getValue("predicate") as Map<String, List<String>>
        val objectMapper = ObjectMapper()
        val conditionedQuestionModel = objectMapper.readValue(
          objectMapper.writeValueAsString(questionModel.questionType.condition.getValue("if_positive")),
          QuestionModel::class.java
        )

        val conditionedQuestion = conditionedQuestionModel.toQuestionDBModel()
        conditionedQuestion.id = questionDao.save(conditionedQuestion)
        processNumberRangeIfPossible(conditionedQuestionModel, conditionedQuestion)
        logger.debug("Conditional question [${conditionedQuestion.questionText}] is inserted as a main question.")

        // Only exact equals can be interpreted
        val predicateText = "exactEquals"
        val predicateAttributes = predicateObject.getValue(predicateText)
        if (predicateAttributes.size == 2 &&
          predicateAttributes[0] == "\${selection}") {
          val optionFilteredText = predicateAttributes[1]
          val matchedOption = options.single { it.optionText == optionFilteredText }
          conditionalQuestionDao.save(ConditionalQuestion(predicateText, matchedOption, conditionedQuestion, question))
          logger.debug("Conditional question is saved.")
        }
      } catch (e: Exception) {
        logger.error("Cannot create conditional question.")
      }
    }
  }

  private fun processNumberRangeIfPossible(questionModel: QuestionModel, question : Question){
    // Process Number Range
    if(questionModel.questionType.type == "number_range"){
      val from = questionModel.questionType.range?.getValue("from")
      val to = questionModel.questionType.range?.getValue("to")
      if(from != null && from is Int && to !=null && to is Int){
        numberRangeDao.save(NumberRange(from, to, question))
      }
    }
  }

  companion object {
    private val logger = LogManager.getLogger(PollService::class.java)
  }
}
