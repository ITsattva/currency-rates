package com.currency.service;

import com.currency.dto.MonoBankResponse;
import com.currency.entity.Rate;
import com.currency.enums.Currency;
import com.currency.enums.Source;
import com.currency.mapper.RateMapper;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonoBankService implements ExchangeRateProvider {
  private final RateMapper rateMapper;
  private final List<Integer> neededCurrenciesCodes = new ArrayList<>(Arrays.asList(980, 978, 840));

  @Value("${application.monobank-url}")
  private String MONOBANK_API_URL;

  private final RestTemplate restTemplate = new RestTemplate();

  @Override
  public Source getSource() {
    return Source.MONOBANK;
  }

  @Override
  public List<Rate> fetchExchangeRates() {
    try {
      MonoBankResponse[] monoResponse =
          restTemplate.getForObject(MONOBANK_API_URL, MonoBankResponse[].class);
      log.debug("Exchange rates fetched successfully");
      return Arrays.stream(Objects.requireNonNull(monoResponse))
          .filter(this::isNeedToAddToDatabase)
          .map(rateMapper::monoResponseToRate)
          .collect(Collectors.toList());
    } catch (RestClientException e) {
      log.error("Error while fetching exchange rates from MonoBank API: {}", e.getMessage());
      throw new RuntimeException("Error while fetching exchange rates from MonoBank API", e);
    }
  }

  private boolean isNeedToAddToDatabase(MonoBankResponse response) {
    return response.getBaseCurrency().equals(Currency.UAH.getCode())
        && neededCurrenciesCodes.contains(response.getCurrency());
  }
}
