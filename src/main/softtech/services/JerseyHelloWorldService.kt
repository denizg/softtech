package main.softtech.services

import main.softtech.dao.IBaseDao
import model.Answer
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

@Path("/show-on-screen")
class JerseyHelloWorldService {
  @Autowired
  lateinit var answerDao: IBaseDao<Answer>
  
  @GET
  @Path("/{message}")
  fun getMsg(@PathParam("message") msg: String): Response? {
    val output = "Message requested : $msg"
    answerDao.hello()
    
    return Response.status(200).entity(output).build()
  }
}