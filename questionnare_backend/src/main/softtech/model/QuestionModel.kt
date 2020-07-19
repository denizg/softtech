
package main.softtech.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import main.softtech.db_model.Question

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionModel (@JsonProperty("question") val question : String,
  @JsonProperty("category") val category : String,
  @JsonProperty("question_type") val questionType : QuestionTypeModel) {
  fun toQuestionDBModel(): Question {
    return Question(question, category, questionType.type)
  }
}
