package ru.pet.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet.telegrambot.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
