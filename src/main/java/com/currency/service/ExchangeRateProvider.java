package com.currency.service;

import com.currency.entity.Rate;
import com.currency.enums.Source;
import java.util.List;

public interface ExchangeRateProvider {
  Source getSource();

  List<Rate> fetchExchangeRates();
}
