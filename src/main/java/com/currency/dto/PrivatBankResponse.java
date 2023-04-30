package com.currency.dto;

import com.currency.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PrivatBankResponse {

  @JsonProperty("ccy")
  private Currency currency;

  @JsonProperty("base_ccy")
  private Currency baseCurrency;

  @JsonProperty("buy")
  private Double buyRate;

  @JsonProperty("sale")
  private Double sellRate;
}
