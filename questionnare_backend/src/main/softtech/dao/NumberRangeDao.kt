package main.softtech.dao

import main.softtech.db_model.NumberRange
import org.apache.log4j.LogManager
import org.springframework.transaction.annotation.Transactional
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

open class NumberRangeDao : BaseDao<NumberRange>() {

  @Transactional
  override fun save(model: NumberRange) : Int{
    return try {
      getCurrentSession().save(model) as Int
    } catch (e: Exception) {
      logger.error("Cannot save option.", e)
      return -1
    }
  }

  @Transactional
  override fun findSingleEntityByField(fieldName: String, fieldValue: String): List<NumberRange> {
    val builder: CriteriaBuilder = getCurrentSession().criteriaBuilder
    val criteria: CriteriaQuery<NumberRange> = builder.createQuery(NumberRange::class.java)
    val myObjectRoot: Root<NumberRange> = criteria.from(NumberRange::class.java)
    val likeRestriction: Predicate = builder.and(
      builder.like(myObjectRoot.get(fieldName), fieldValue)
    )
    criteria.select(myObjectRoot).where(likeRestriction)
    val query: TypedQuery<NumberRange> = getCurrentSession().createQuery(criteria)
    return query.resultList
  }

  override fun getAll(): List<NumberRange> {
    TODO("Not yet implemented")
  }

  override fun findSingleEntityById(id: Int) : NumberRange?{
    TODO("Not yet implemented")
  }

  companion object {
    private val logger = LogManager.getLogger(NumberRangeDao::class.java)
  }
}
