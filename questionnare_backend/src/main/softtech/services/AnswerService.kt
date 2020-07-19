package main.softtech.services

import com.fasterxml.jackson.databind.ObjectMapper
import main.softtech.dao.BaseDao
import main.softtech.db_model.Answer
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

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  fun saveAnswer(answer: Answer): Response {
    return try {
      answerDao.save(answer)
      logger.debug("Answer is created successfully.")
      return Response.ok(ObjectMapper().writeValueAsString(mapOf("id" to "1"))).status(201).build()
    } catch (e: Exception) {
      logger.error("Cannot create answer.", e)
      Response.serverError().entity("Cannot insert Answer. Error is [${e.message}]").build()
    }
  }

  companion object {
    private val logger = LogManager.getLogger(AnswerService::class.java)
  }
}
