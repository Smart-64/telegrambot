package ru.pet.telegrambot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pet.telegrambot.dto.UserDto;
import ru.pet.telegrambot.model.User;
import ru.pet.telegrambot.model.mapper.UserMapper;
import ru.pet.telegrambot.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private Update update;


    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    // TODO: добавить обработку ошибок;
    // TODO: добавить обработку сообщений с update;
    public User save(UserDto dto) {
        User user = mapper.toUser(dto);
        repository.save(user);
        return user;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
}
