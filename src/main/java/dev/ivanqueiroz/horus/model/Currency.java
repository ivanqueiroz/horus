package dev.ivanqueiroz.horus.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long userId;
  private String currencySource;
  private String currencyDestiny;
  private BigDecimal amount;
  private BigDecimal currencyRate;
  private BigDecimal result;
  private Date date;
}
