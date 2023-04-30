package com.currency.updater;

import com.currency.entity.Rate;
import com.currency.repository.RateRepository;
import com.currency.service.ExchangeRateProvider;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class RateUpdaterService {
  private final List<ExchangeRateProvider> exchangeRateProviders;
  private final RateRepository rateRepository;

  public void updateExchangeRates() {
    List<Rate> exchangeRates = new ArrayList<>();
    for (ExchangeRateProvider exchangeRateProvider : exchangeRateProviders) {
      try {
        exchangeRates.addAll(exchangeRateProvider.fetchExchangeRates());
        log.info("Exchange rates from {} retrieved successfully", exchangeRateProvider.getSource());
      } catch (Exception e) {
        log.error(
            "Error while getting exchange rates from {}", exchangeRateProvider.getSource(), e);
      }
    }
    rateRepository.saveAll(exchangeRates);
    log.info("Exchange rates saved to database successfully");
  }
}
