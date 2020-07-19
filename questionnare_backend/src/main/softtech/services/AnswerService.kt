package main.softtech.services

import main.softtech.dao.BaseDao
import main.softtech.db_model.Answer
import main.softtech.db_model.Option
import main.softtech.db_model.Question
import main.softtech.model.AnswerRequestModel
import org.apache.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/answers")
class AnswerService {
  @Autowired
  lateinit var answerDao: BaseDao<Answer>

  @Autowired
  lateinit var optionDao: BaseDao<Option>

  @Autowired
  lateinit var questionDao: BaseDao<Question>

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/singleAnswer")
  fun saveAnswer(answer: Answer): Response {
    return try {
      answerDao.save(answer)
      logger.debug("Answer is created successfully.")
      return Response.ok().status(201).build()
    } catch (e: Exception) {
      logger.error("Cannot create answer.", e)
      Response.serverError().entity("Cannot insert Answer. Error is [${e.message}]").build()
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  fun saveAnswers(answers: List<AnswerRequestModel>): Response {
    // All answers should be submitted at once
    return try {
      for (answer in answers) {
        val question = questionDao.findSingleEntityById(answer.questionId)
        val pair = if (answer.questionType == "number_range") {
          Pair(answer.selection, null)
        } else {
          val option = answer.selection.let { optionDao.findSingleEntityById(it) }
          Pair(null, option)
        }
        question?.let { answerDao.save(Answer(numberValue = pair.first, option = pair.second, question = question)) }
      }
      logger.debug("Answers are created successfully.")
      return Response.ok().status(201).build()
    } catch (e: Exception) {
      logger.error("Cannot create answers.", e)
      Response.serverError().entity("Cannot create answers. Error is [${e.message}]").build()
    }
  }

  companion object {
    private val logger = LogManager.getLogger(AnswerService::class.java)
  }
}
