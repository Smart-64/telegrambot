package ru.pet.telegrambot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pet.telegrambot.dto.UserDto;
import ru.pet.telegrambot.service.MessageService;
import ru.pet.telegrambot.service.UserService;


/**
 * Телеграм бот, предоставляющий актуальный курс валют из ЦБ РФ.
 */
@Component
public class Bot extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(Bot.class);
    private final String botToken;
    private final String botUsername;
    private final UserService userService;
    private final MessageService messageService;

    public Bot(
            @Value("${telegram.bot.username}") String botUsername,
            @Value("${telegram.bot.token}") String botToken, UserService userService, MessageService messageService) {
        super(botToken);
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.userService = userService;
        this.messageService = messageService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            UserDto userDto = new UserDto(
                    update.getMessage().getFrom().getId(),
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getText(),
                    update.getMessage().getDate());
            userService.save(userDto);

            SendMessage sendMessage = messageService.createSendMessage(update);
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
