package main.softtech.dao

import main.softtech.db_model.Option
import org.apache.log4j.LogManager
import org.springframework.transaction.annotation.Transactional
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

open class OptionDao : BaseDao<Option>() {

  @Transactional
  override fun save(model: Option): Int {
    return try {
      getCurrentSession().save(model) as Int
    } catch (e: Exception) {
      logger.error("Cannot save option.", e)
      return -1
    }
  }

  @Transactional
  override fun findSingleEntityByField(fieldName: String, fieldValue: String): List<Option> {
    val builder: CriteriaBuilder = getCurrentSession().criteriaBuilder
    val criteria: CriteriaQuery<Option> = builder.createQuery(Option::class.java)
    val myObjectRoot: Root<Option> = criteria.from(Option::class.java)
    val likeRestriction: Predicate = builder.and(
      builder.like(myObjectRoot.get(fieldName), fieldValue)
    )
    criteria.select(myObjectRoot).where(likeRestriction)
    val query: TypedQuery<Option> = getCurrentSession().createQuery(criteria)
    return query.resultList
  }

  override fun getAll(): List<Option> {
    TODO("Not yet implemented")
  }

  override fun findSingleEntityById(id: Int): Option? {
    return try {
      getCurrentSession().get(Option::class.java, id) as Option
    } catch (e: Exception) {
      logger.error("Cannot read Option with Id $id from database.")
      null
    }
  }

  companion object {
    private val logger = LogManager.getLogger(OptionDao::class.java)
  }
}
