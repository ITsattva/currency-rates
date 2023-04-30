package com.currency.entity;

import com.currency.enums.Currency;
import com.currency.enums.Source;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "rate")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "source")
  @Enumerated(value = EnumType.STRING)
  private Source source;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "base_currency")
  @Enumerated(value = EnumType.STRING)
  private Currency baseCurrency;

  @Column(name = "exchange_currency")
  @Enumerated(value = EnumType.STRING)
  private Currency exchangeCurrency;

  @Column(name = "buy_rate")
  private Double buyRate;

  @Column(name = "sell_rate")
  private Double sellRate;
}
