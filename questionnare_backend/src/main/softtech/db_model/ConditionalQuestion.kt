package main.softtech.db_model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity(name = "conditional_question")
class ConditionalQuestion(
  @Column(name = "predicate")
  val predicate: String,
  @ManyToOne
  @JoinColumn(name = "referenced_option_fk", nullable = true)
  val option: Option?,
  @OneToOne
  @JoinColumn(name = "referenced_question_fk", nullable = false)
  val referencedQuestion: Question,
  @OneToOne
  @JoinColumn(name = "conditioned_question_fk", nullable = false)
  val conditionedQuestion: Question
) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  val id: Int? = null
}
