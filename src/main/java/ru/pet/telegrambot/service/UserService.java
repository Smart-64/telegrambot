package ru.pet.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pet.telegrambot.dto.UserDto;
import ru.pet.telegrambot.model.User;
import ru.pet.telegrambot.model.mapper.UserMapper;
import ru.pet.telegrambot.repository.UserRepository;

@Service
public class UserService {
    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;
    private final UserMapper mapper;
    private Update update;


    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(UserDto dto) {
        User user = mapper.toUser(dto);
        repository.save(user);
    }

    public SendMessage createSendMessage() {
        String chatId = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();
        String firstName = update.getMessage().getFrom().getFirstName();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(firstName + ", твое сообщение '" + message + "' сохранено в БД");
        log.info("{}, твое сообщение '{}' сохранено в БД", firstName, message);

        return sendMessage;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
}
