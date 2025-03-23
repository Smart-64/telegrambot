package ru.pet.telegrambot.dto;

import java.time.LocalDateTime;

public record UserDto(Long userId,
                      String firstName,
                      String messageText,
                      Integer unixTime) {
}
