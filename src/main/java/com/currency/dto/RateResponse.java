package com.currency.dto;

import com.currency.enums.Currency;
import com.currency.enums.Source;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RateResponse {
  @Schema(
      description = "Source of rates",
      allowableValues = {"PRIVATBANK", "MONOBANK", "MINFIN"})
  private Source source;

  @Schema(description = "Base currency", example = "UAH")
  private Currency baseCurrency;

  @Schema(description = "Exchange currency", example = "USD")
  private Currency exchangeCurrency;

  @Schema(description = "Average rate for buy", example = "39.1")
  private Double avgBuyRate;

  @Schema(description = "Average rate for sell", example = "38.9")
  private Double avgSellRate;
}
