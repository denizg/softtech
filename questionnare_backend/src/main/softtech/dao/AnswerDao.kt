package main.softtech.dao

import main.softtech.db_model.Answer
import org.apache.log4j.LogManager
import org.springframework.transaction.annotation.Transactional
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

open class AnswerDao : BaseDao<Answer>() {

  @Transactional
  override fun save(model: Answer) : Int{
    return try {
      getCurrentSession().save(model) as Int
    } catch (e: Exception) {
      logger.error("Cannot save answer.", e)
      return -1
    }
  }

  @Transactional
  override fun findSingleEntityByField(fieldName: String, fieldValue: String): List<Answer> {
    val builder: CriteriaBuilder = getCurrentSession().criteriaBuilder
    val criteria: CriteriaQuery<Answer> = builder.createQuery(Answer::class.java)
    val myObjectRoot: Root<Answer> = criteria.from(Answer::class.java)
    val likeRestriction: Predicate = builder.and(
      builder.like(myObjectRoot.get(fieldName), fieldValue)
    )
    criteria.select(myObjectRoot).where(likeRestriction)
    val query: TypedQuery<Answer> = getCurrentSession().createQuery(criteria)
    return query.resultList
  }

  companion object {
    private val logger = LogManager.getLogger(AnswerDao::class.java)
  }

  override fun getAll(): List<Answer> {
    TODO("Not yet implemented")
  }

  override fun findSingleEntityById(id: Int) : Answer? {
    TODO("Not yet implemented")
  }
}
