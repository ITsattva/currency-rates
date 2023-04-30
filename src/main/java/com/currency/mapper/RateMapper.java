package com.currency.mapper;

import static com.currency.enums.Source.*;

import com.currency.dto.MinFinResponse;
import com.currency.dto.MonoBankResponse;
import com.currency.dto.PrivatBankResponse;
import com.currency.entity.Rate;
import com.currency.enums.Currency;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RateMapper {

  public Rate privatResponseToRate(PrivatBankResponse response) {
    if (response == null) {
      throw new MappingException("Response object is null");
    }
    try {
      return Rate.builder()
          .source(PRIVATBANK)
          .baseCurrency(response.getBaseCurrency())
          .exchangeCurrency(response.getCurrency())
          .sellRate(response.getSellRate())
          .buyRate(response.getBuyRate())
          .date(LocalDate.now())
          .build();
    } catch (Exception e) {
      throw new MappingException("Error mapping response to Rate", e);
    }
  }

  public Rate monoResponseToRate(MonoBankResponse response) {
    if (response == null) {
      throw new MappingException("Response object is null");
    }
    try {
      return Rate.builder()
          .source(MONOBANK)
          .baseCurrency(Currency.getCurrencyByCode(response.getBaseCurrency()))
          .exchangeCurrency(Currency.getCurrencyByCode(response.getCurrency()))
          .sellRate(response.getSellRate())
          .buyRate(response.getBuyRate())
          .date(LocalDate.now())
          .build();
    } catch (Exception e) {
      throw new MappingException("Error mapping response to Rate", e);
    }
  }

  public Rate minfinResponseToRate(MinFinResponse response) {
    if (response == null) {
      throw new MappingException("Response object is null");
    }
    try {
      return Rate.builder()
          .source(MINFIN)
          .baseCurrency(response.getBaseCurrency())
          .exchangeCurrency(Currency.valueOf(response.getCurrency().toUpperCase()))
          .sellRate(response.getSellRate())
          .buyRate(response.getBuyRate())
          .date(LocalDate.now())
          .build();
    } catch (Exception e) {
      throw new MappingException("Error mapping response to Rate", e);
    }
  }
}
