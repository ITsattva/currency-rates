package com.currency.service;

import com.currency.dto.PrivatBankResponse;
import com.currency.entity.Rate;
import com.currency.enums.Source;
import com.currency.mapper.RateMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
public class PrivatBankService implements ExchangeRateProvider {
  @Value("${application.privatbank-url}")
  private String PRIVAT_API_URL;

  private final RestTemplate restTemplate = new RestTemplate();
  private final RateMapper rateMapper;

  @Override
  public Source getSource() {
    return Source.PRIVATBANK;
  }

  @Override
  public List<Rate> fetchExchangeRates() {
    try {
      log.debug("Fetching exchange rates from PrivatBank API...");
      PrivatBankResponse[] privatResponse =
          restTemplate.getForObject(PRIVAT_API_URL, PrivatBankResponse[].class);
      log.debug("Exchange rates fetched successfully");
      return Arrays.stream(Objects.requireNonNull(privatResponse))
          .map(rateMapper::privatResponseToRate)
          .collect(Collectors.toList());
    } catch (RestClientException e) {
      log.error("Error while fetching exchange rates from PrivatBank API: {}", e.getMessage());
      throw new RuntimeException("Error while fetching exchange rates from PrivatBank API", e);
    }
  }
}
