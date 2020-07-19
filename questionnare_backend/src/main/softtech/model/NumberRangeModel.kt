package main.softtech.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class NumberRangeModel(
  @JsonProperty("number_range_id") val numberRangeId: Int,
  @JsonProperty("from") val from: Int,
  @JsonProperty("to") val to: Int
)
