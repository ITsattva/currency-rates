package com.currency.service;

import com.currency.dto.MinFinResponse;
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
@RequiredArgsConstructor
@Slf4j
public class MinFinService implements ExchangeRateProvider {
  @Value("${application.minfin-token}")
  private String MINFIN_TOKEN;

  @Value("${application.minfin-url}")
  private String MINFIN_API;

  private final RestTemplate restTemplate = new RestTemplate();
  private final RateMapper rateMapper;

  @Override
  public Source getSource() {
    return Source.MINFIN;
  }

  @Override
  public List<Rate> fetchExchangeRates() {
    try {
      log.debug("Fetching exchange rates from MinFin API...");
      MinFinResponse[] minfinResponse =
          restTemplate.getForObject(MINFIN_API + MINFIN_TOKEN, MinFinResponse[].class);
      log.debug("Exchange rates fetched successfully");
      return Arrays.stream(Objects.requireNonNull(minfinResponse))
          .map(rateMapper::minfinResponseToRate)
          .collect(Collectors.toList());
    } catch (RestClientException e) {
      log.error("Error while fetching exchange rates from MinFin API: {}", e.getMessage());
      throw new RuntimeException("Error while fetching exchange rates from MinFin API", e);
    }
  }
}
