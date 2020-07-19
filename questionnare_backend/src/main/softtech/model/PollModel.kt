package main.softtech.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PollModel(
  @JsonProperty("categories") val categories: List<String>,
  @JsonProperty("questions") val questions: List<QuestionModel>
)
