package main.softtech.dao

import org.apache.log4j.LogManager
import org.hibernate.Session
import org.hibernate.SessionFactory
import javax.annotation.Resource

abstract class BaseDao<T> {
  @Resource
  private lateinit var sessionFactory: SessionFactory
  abstract fun save(model: T): Int
  abstract fun findSingleEntityByField(fieldName: String, fieldValue: String): List<T>
  abstract fun getAll(): List<T>
  abstract fun findSingleEntityById(id: Int) : T?

  fun getCurrentSession(): Session {
    return try {
      sessionFactory.currentSession
    } catch (e: Exception) {
      logger.debug("Cannot reach current session, a new session is opened.")
      sessionFactory.openSession()
    }
  }

  companion object {
    private val logger = LogManager.getLogger(BaseDao::class.java)
  }
}
