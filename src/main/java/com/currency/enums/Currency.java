package com.currency.enums;

import com.currency.exception.CurrencyException;
import java.util.HashMap;
import java.util.Map;

public enum Currency {
  UAH(980),
  EUR(978),
  USD(840);

  private final Integer code;
  private static final Map<Integer, Currency> codeMap = new HashMap<>();

  static {
    for (Currency currency : values()) {
      codeMap.put(currency.getCode(), currency);
    }
  }

  Currency(Integer code) {
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }

  public static Currency getCurrencyByCode(Integer code) {
    Currency currency = codeMap.get(code);
    if (currency == null) {
      throw new CurrencyException(
          String.format("Currency with code '%d' could not be found", code));
    }
    return currency;
  }
}
