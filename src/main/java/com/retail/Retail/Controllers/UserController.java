package com.retail.Retail.Controllers;
import com.retail.Retail.Models.User;
import com.retail.Retail.Repositories.UserRepo;
import com.retail.Retail.Services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepo userRepo;


    //просмотр списка юзеров
    @GetMapping("/user-list-admin")
    public String showUserList(Model model) {
        model.addAttribute("user", userRepo.findAll());
        log.info("--- Отображён текущий список пользователей панели управления ---");
        return "/user-templates/user-list-admin";
    }


    //переход на страницу создания юзера
    @GetMapping("/user-create-admin")
    public String showUserCreateForm() {
        return "/user-templates/user-create-admin";
    }


    //сохранение созданного юзера
    @PostMapping("/user-create-admin")
    public String saveCreatedUser(User user) {
        userService.createUser(user);
        log.info("--- Новый пользователь "+user.getName()+" успешно создан ---");
        return "redirect:/user-list-admin";
    }


    //отображение страницы редактирования юзера
    @GetMapping("/user-edit-admin/{id}")
    public String showUserEditForm(@PathVariable(value = "id") long id, Model model) {
        userService.findUserById(id, model);
        return "/user-templates/user-edit-admin";
    }


    //сохранение отредактированного юзера
     @PostMapping("/user-edit-admin/{id}")
     public String userEditedSave(@PathVariable(value = "id") long id,
                                 @RequestParam boolean active,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String patronymic,
                                 @RequestParam String phone,
                                 @RequestParam String email,
                                 @RequestParam String password) {
         userService.updateUser(id, active, name, surname, patronymic, phone, email, password);
         log.info("--- Пользователь успешно отредактирован ---");
         return "redirect:/user-list-admin";
     }


    //удаление юзера
    @GetMapping("/user-remove-admin/{id}")
    public String userRemoveForm(@PathVariable(value = "id") long id) {
        userService.removeUser(id);
        log.info("--- Пользователь успешно удалён из базы данных ---");
        return "redirect:/user-list-admin";
    }
}
