package ru.pet.telegrambot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


/**
 * Телеграм бот, предоставляющий актуальный курс валют из ЦБ РФ.
 *
 */
@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botUsername;

    public Bot(
            @Value("${telegram.bot.username}") String botUsername,
            @Value("${telegram.bot.token}") String botToken) {
        super(botToken);
        this.botToken = botToken;
        this.botUsername = botUsername;
        log.info("Bot initialized with username: {}", botUsername);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String message = update.getMessage().getText();
            String firstName = update.getMessage().getFrom().getFirstName();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(firstName + " отправил: " + message);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Ошибка при отправке ответного сообщения пользователю: {}", e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }
}
