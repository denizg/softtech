package main.softtech.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "answer")
class Answer(
  @Column(name = "number_value")
  val numberValue: Int?,
  @Column(name = "option_fk")
  val optionKey: Int,
  @Column(name = "question_fk")
  val questionKey: Int
) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  val id: Int? = null
}