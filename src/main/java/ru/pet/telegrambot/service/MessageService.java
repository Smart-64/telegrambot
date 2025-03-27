package ru.pet.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private final CurrencyService currencyService;

    public MessageService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public SendMessage createSendMessage(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();
        String firstName = update.getMessage().getFrom().getFirstName();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        try {
            String rate = String.valueOf(currencyService.getRate(message));
            sendMessage.setText(rate);
        } catch (IllegalArgumentException e) {
            sendMessage.setText("Ошибка! " + e.getMessage());
        }

        log.info("{}, твое сообщение '{}' сохранено в БД", firstName, message);

        return sendMessage;
    }
}
