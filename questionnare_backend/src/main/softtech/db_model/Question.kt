package main.softtech.db_model

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity(name = "question")
class Question(
  @Column(name = "question_text")
  val questionText: String,
  @Column(name = "category")
  val category: String,
  @Column(name = "question_type")
  val questionType: String
) {
  constructor() : this("", "", "") {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int? = null

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
  val options : Set<Option>? = null

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
  val numberRange : NumberRange? = null
}
