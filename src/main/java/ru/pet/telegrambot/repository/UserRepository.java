package ru.pet.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pet.telegrambot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
