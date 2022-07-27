package com.retail.Retail.Services;
import  com.retail.Retail.Models.Role;
import com.retail.Retail.Models.User;
import com.retail.Retail.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    //создание и сохранение созданного юзера
    public void createUser(User user, String userEmail) {
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Сохранён новый пользователь с электронной почтой: ", userEmail);
        userRepo.save(user);
    }


    //сохранение отредактированного юзера
    public void updateUser(@PathVariable(value = "id") long id,
                           @RequestParam boolean active,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String patronymic,
                           @RequestParam String phone,
                           @RequestParam String email,
                           @RequestParam String password) {
        User user = userRepo.findById(id).orElseThrow();
        if (!active) {
            user.setActive(active);
            user.setName(name);
            user.setSurname(surname);
            user.setPatronymic(patronymic);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.getRoles().clear();
            user.getRoles().add(Role.ROLE_DISABLED);
            userRepo.save(user);
        } else {
            user.setActive(active);
            user.setName(name);
            user.setSurname(surname);
            user.setPatronymic(patronymic);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.getRoles().clear();
            user.getRoles().add(Role.ROLE_USER);
            userRepo.save(user);
        }
    }


    public void findUserById(long id, Model model) {
        Optional<User> user = userRepo.findById(id);
        ArrayList<User> result = new ArrayList<>();
        user.ifPresent(result::add);
        model.addAttribute("userss", result);
    }

    //удаление юзера
    public void removeUser(long id) {
        User user = userRepo.getById(id);
        userRepo.delete(user);
        log.info("--- Пользователь успешно удалён из базы данных ---");
    }

    //просмотр списка юзеров
    public void findAllUsers(Model model) {
        model.addAttribute("user", userRepo.findAll());
        log.info("--- Отображён текущий список пользователей панели управления ---");
    }
}
