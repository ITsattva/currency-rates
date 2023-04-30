package com.currency.repository;

import com.currency.dto.RateResponse;
import com.currency.entity.Rate;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
  @Query(
      "SELECT new com.currency.dto.RateResponse(r.source, r.baseCurrency, r.exchangeCurrency, AVG(r.buyRate), AVG(r.sellRate)) "
          + "FROM Rate r "
          + "GROUP BY r.source, r.baseCurrency, r.exchangeCurrency")
  List<RateResponse> findAverageSellRateBySource();

  @Query(
      "SELECT new com.currency.dto.RateResponse(r.source, r.baseCurrency, r.exchangeCurrency, AVG(r.buyRate), AVG(r.sellRate)) "
          + "FROM Rate r "
          + "WHERE r.date BETWEEN :startDate AND :endDate "
          + "GROUP BY r.source, r.baseCurrency, r.exchangeCurrency")
  List<RateResponse> findAverageSellRateBySourceByPeriod(LocalDate startDate, LocalDate endDate);
}
