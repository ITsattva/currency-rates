package com.currency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MonoBankResponse {

  @JsonProperty("currencyCodeB")
  private Integer baseCurrency;

  @JsonProperty("currencyCodeA")
  private Integer currency;

  @JsonProperty("rateBuy")
  private Double buyRate;

  @JsonProperty("rateSell")
  private Double sellRate;
}
