package ru.pet.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet.telegrambot.model.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findByDescription(String description);
}
