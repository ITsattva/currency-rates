package com.currency.api;

import com.currency.dto.RateResponse;
import com.currency.service.RateService;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Rates API endpoints")
@RequestMapping("api/rates")
@Data
public class RateController {
  private final RateService rateService;

  @GetMapping()
  @ApiResponse(
      code = 200,
      message =
          "This endpoint should return a list of exchange rates for all sources, with average market rates")
  public List<RateResponse> getCurrentRates() {
    return rateService.getAverageRates();
  }

  @GetMapping("/period")
  @ApiResponse(
      code = 200,
      message =
          "This endpoint should return a list of exchange rates for all sources, with average market rates for specified period in params")
  public List<RateResponse> getRatesForTimePeriod(
      @RequestParam(name = "from")
          @Parameter(
              description = "Start of the period in format DD/MM/YYYY",
              example = "05/07/2022")
          LocalDate startDate,
      @RequestParam(name = "to")
          @Parameter(description = "End of the period in format DD/MM/YYYY", example = "05/07/2023")
          LocalDate endDate) {
    return rateService.getAverageRatesForTimePeriod(startDate, endDate);
  }
}
