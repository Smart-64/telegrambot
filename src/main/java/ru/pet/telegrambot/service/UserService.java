package ru.pet.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.pet.telegrambot.dto.UserDto;
import ru.pet.telegrambot.model.User;
import ru.pet.telegrambot.model.mapper.UserMapper;
import ru.pet.telegrambot.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;
    private final UserMapper mapper;


    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(UserDto dto) {
        User user = mapper.toUser(dto);
        log.info("Сохрание пользователя {} в БД", user.getFirstName());
        repository.save(user);
    }

    public List<User> getAll() {
        log.info("Получение списка всех пользователей");
        return repository.findAll();
    }

}
