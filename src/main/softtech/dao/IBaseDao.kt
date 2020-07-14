package main.softtech.dao

interface IBaseDao<T> {
  fun save(model: T)
  fun update(model: T)
  fun saveOrUpdate(model: T)
  fun findSingleEntityByField(fieldName: String, fieldValue: String): List<T>
  fun hello(){
    val x = 8
  }
}