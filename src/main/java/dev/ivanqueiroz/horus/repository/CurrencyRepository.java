package dev.ivanqueiroz.horus.repository;

import dev.ivanqueiroz.horus.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

  List<Currency> findByUserId(Long userId);
}
