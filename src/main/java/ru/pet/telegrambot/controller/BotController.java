package ru.pet.telegrambot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pet.telegrambot.model.User;
import ru.pet.telegrambot.service.CurrencyService;
import ru.pet.telegrambot.service.MessageService;
import ru.pet.telegrambot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/bot")
public class BotController {
    private final UserService userService;
    private final CurrencyService currencyService;
    private final MessageService messageService;

    public BotController(UserService userService, CurrencyService currencyService, MessageService messageService) {
        this.userService = userService;
        this.currencyService = currencyService;
        this.messageService = messageService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/currency/update")
    public ResponseEntity<String> updateCurrencyRates() {
        currencyService.updateRates();
        return ResponseEntity.ok("Currency rates updated");
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam Long chatId, @RequestParam String text) {
        return ResponseEntity.ok("Отправлено сообщение: " + text);
    }

}
