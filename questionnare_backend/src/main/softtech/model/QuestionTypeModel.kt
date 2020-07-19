
package main.softtech.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionTypeModel (
  @JsonProperty("type") val type : String,
  @JsonProperty("options") val options : List<String>?,
  @JsonProperty("condition") val condition : Map<String, Any>? = HashMap(),
  @JsonProperty("range") val range : Map<String, Any>? = HashMap()
)
