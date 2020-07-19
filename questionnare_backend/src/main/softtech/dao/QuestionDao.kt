package main.softtech.dao

import main.softtech.db_model.Option
import main.softtech.db_model.Question
import org.apache.log4j.LogManager
import org.springframework.transaction.annotation.Transactional
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

open class QuestionDao : BaseDao<Question>() {

  @Transactional
  override fun save(model: Question): Int {
    return try {
      getCurrentSession().save(model) as Int
    } catch (e: Exception) {
      logger.error("Cannot save question.", e)
      return -1
    }
  }

  @Transactional
  override fun findSingleEntityByField(fieldName: String, fieldValue: String): List<Question> {
    val builder: CriteriaBuilder = getCurrentSession().criteriaBuilder
    val criteria: CriteriaQuery<Question> = builder.createQuery(Question::class.java)
    val myObjectRoot: Root<Question> = criteria.from(Question::class.java)
    val likeRestriction: Predicate = builder.and(
      builder.like(myObjectRoot.get(fieldName), fieldValue)
    )
    criteria.select(myObjectRoot).where(likeRestriction)
    val query: TypedQuery<Question> = getCurrentSession().createQuery(criteria)
    return query.resultList
  }

  @Transactional
  override fun getAll(): List<Question> {
    val builder: CriteriaBuilder = getCurrentSession().criteriaBuilder
    val criteria: CriteriaQuery<Question> = builder.createQuery(Question::class.java)
    val myObjectRoot: Root<Question> = criteria.from(Question::class.java)
    criteria.select(myObjectRoot)
    val query: TypedQuery<Question> = getCurrentSession().createQuery(criteria)
    query.resultList.forEach {
      it.numberRange
      it.options?.size
    }
    return query.resultList
  }

  override fun findSingleEntityById(id: Int) : Question?{
    return try {
      getCurrentSession().get(Question::class.java, id) as Question
    } catch (e: Exception) {
      logger.error("Cannot read Question with Id $id from database.")
      null
    }
  }

  companion object {
    private val logger = LogManager.getLogger(QuestionDao::class.java)
  }
}
