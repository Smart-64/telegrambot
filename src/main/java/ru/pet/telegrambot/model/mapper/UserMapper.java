package ru.pet.telegrambot.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.pet.telegrambot.dto.UserDto;
import ru.pet.telegrambot.model.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "unixTime", target = "messageReceivedAt", qualifiedByName = "convertUnixToLocalDateTime")
    User toUser(UserDto dto);

    @Named("convertUnixToLocalDateTime")
    default LocalDateTime convertUnixToLocalDateTime(Integer unixTime) {
        return unixTime == null ? null : LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.UTC);
    }

}
