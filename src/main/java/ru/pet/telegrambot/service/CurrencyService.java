package ru.pet.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.pet.telegrambot.model.Currency;
import ru.pet.telegrambot.repository.CurrencyRepository;

@Service
public class CurrencyService {
    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);

    private final CurrencyRepository repository;

    public CurrencyService(CurrencyRepository repository) {
        this.repository = repository;
    }

    public void updateRates() {
        log.info("Обноление валюты");
    }

    public Double getRate(String description) {
        return repository.findByDescription(description)
                .map(Currency::getRate)
                .orElseThrow(() -> new IllegalArgumentException("Валюта не найдена: " + description));
    }
}
