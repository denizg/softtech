package main.softtech.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PollItemModel(
  @JsonProperty("category") val category: String,
  @JsonProperty("question_type") val questionType: String,
  @JsonProperty("question_text") val label: String,
  @JsonProperty("question_id") val value: Int,
  @JsonProperty("options") val options: List<OptionModel>?,
  @JsonProperty("numberRange") val numberRange: NumberRangeModel?
)

