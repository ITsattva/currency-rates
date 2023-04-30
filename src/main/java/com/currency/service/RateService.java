package com.currency.service;

import com.currency.dto.RateResponse;
import com.currency.exception.WrongParameterException;
import com.currency.repository.RateRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateService {
  private final RateRepository rateRepository;

  public List<RateResponse> getAverageRates() {
    return rateRepository.findAverageSellRateBySource();
  }

  public List<RateResponse> getAverageRatesForTimePeriod(LocalDate start, LocalDate end) {
    if (end.isBefore(start)) {
      throw new WrongParameterException(
          String.format("Wrong parameters: %s date should be after %s", start, end),
          HttpStatus.BAD_REQUEST);
    }
    return rateRepository.findAverageSellRateBySourceByPeriod(start, end);
  }
}
