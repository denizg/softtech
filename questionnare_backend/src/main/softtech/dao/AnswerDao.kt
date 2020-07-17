package main.softtech.dao

import main.softtech.model.Answer
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

open class AnswerDao : IBaseDao<Answer> {
  @Resource
  private lateinit var sessionFactory: SessionFactory

  override fun hello() {
//    TODO("Not yet implemented")
    val a = 2
    val b = 4
  }

  @Transactional
  override fun saveOrUpdate(model: Answer) {
    getCurrentSession().saveOrUpdate(model)
  }

  @Transactional
  override fun update(model: Answer) {
    getCurrentSession().update(model)
  }

  @Transactional
  override fun save(model: Answer) {
    getCurrentSession().save(model)
  }

  @Transactional
  override fun findSingleEntityByField(fieldName: String, fieldValue: String): List<Answer> {
    val builder: CriteriaBuilder = sessionFactory.currentSession.criteriaBuilder
    val criteria: CriteriaQuery<Answer> = builder.createQuery(Answer::class.java)
    val myObjectRoot: Root<Answer> = criteria.from(Answer::class.java)
//    val joinObject: Join<Answer, JoinObject> = myObjectRoot.join<Any, Any>("joinObject")
    val likeRestriction: Predicate = builder.and(
      builder.like(myObjectRoot.get(fieldName), fieldValue)
    )
//    criteria.select(myObjectRoot).where(joinObject.get("id").`in`(ids), likeRestriction)
    criteria.select(myObjectRoot).where(likeRestriction)
    val query: TypedQuery<Answer> = sessionFactory.currentSession.createQuery(criteria)
    return query.resultList
  }

  private fun getCurrentSession(): Session {
    return try {
      sessionFactory.currentSession
    } catch (e: Exception) {
      sessionFactory.openSession()
    }
  }
}
