package main.softtech.db_model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity(name = "answer")
class Answer(
  @Column(name = "number_value")
  val numberValue: Int?,
  @OneToOne
  @JoinColumn(name = "option_fk", nullable = true)
  val option: Option?,
  @ManyToOne
  @JoinColumn(name = "question_fk", nullable = false)
  val question: Question
) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  val id: Int? = null
}
