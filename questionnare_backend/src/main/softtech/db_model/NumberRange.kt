package main.softtech.db_model

import main.softtech.db_model.Question
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity(name = "number_range")
class NumberRange(
  @Column(name = "from_range")
  val fromRange: Int,
  @Column(name = "to_range")
  val toRange: Int,
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_fk", nullable = false)
  val question: Question
) {
  constructor() : this(0,0, Question()) {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int? = null
}
