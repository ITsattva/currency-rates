package com.currency.dto;

import com.currency.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MinFinResponse {

  @JsonProperty("currency")
  private String currency;

  private Currency baseCurrency = Currency.UAH;

  @JsonProperty("ask")
  private Double buyRate;

  @JsonProperty("bid")
  private Double sellRate;
}
