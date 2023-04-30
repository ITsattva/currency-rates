package com.currency.scheduler;

import com.currency.updater.RateUpdaterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateSyncScheduler {
  private final RateUpdaterService rateUpdaterService;

  @Scheduled(cron = "${application.rate.sync.cron}")
  public void syncExchangeRates() {
    log.info("Starting exchange rates synchronization");

    try {
      rateUpdaterService.updateExchangeRates();
      log.info("Exchange rates synchronization completed successfully");
    } catch (Exception e) {
      log.error("Exchange rates synchronization failed", e);
    }
  }
}
