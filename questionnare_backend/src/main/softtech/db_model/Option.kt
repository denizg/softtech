package main.softtech.db_model

import main.softtech.db_model.Question
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "option")
class Option(
  @Column(name = "option_text")
  val optionText: String,
  @ManyToOne
  @JoinColumn(name = "question_fk", nullable = false)
  val question: Question
) {
  constructor() : this("", Question())

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int? = null
}
