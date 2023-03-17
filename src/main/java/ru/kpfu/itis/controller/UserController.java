package ru.kpfu.itis.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.CreateUserRequestDto;
import ru.kpfu.itis.dto.UserResponseDto;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.security.CustomUserDetails;
import ru.kpfu.itis.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/userProfile")
    public String showProfile(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("user", user);
        return "userProfile";
    }

    @GetMapping("/anotherUser")
    public String showAnotherUser(Model model, @RequestParam String name) {
        User user = userRepository.getUserByName(name).orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("user", user);
        return "anotherUser";
    }

    @ResponseBody
    @GetMapping(value = {"/users/{id}", "users"})
    public List<UserResponseDto> user(@PathVariable(required = false) Optional<Integer> id) {
        if (id.isPresent()) {
            return List.of(userService.findById(id.get()).get());
        } else {
            return userService.findAll();
        }
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("Ivan"));
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute CreateUserRequestDto user) {
        userService.create(user);
        return "sign_up_success";
    }
}

//@RestController
//public class UserController {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserController(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping("/users/{id}")
//    public List<UserResponseDto> user(@PathVariable(required = false) Optional<Integer> id){
//        if (id.isPresent()) {
//            return userRepository.findAllById(List.of(id.get()))
//                    .stream()
//                    .map(UserResponseDto::fromEntity)
//                    .toList();
//        } else {
//            return userRepository.findAll()
//                    .stream()
//                    .map(UserResponseDto::fromEntity)
//                    .toList();
//        }
//    }
//    @GetMapping("/hello")
//    public String hello(@RequestParam Optional<String> name){
//        return String.format("Hello, %s!", name.orElse("Ivan"));
//    }
//
//    @PostMapping("/user")
//    public void createUser(@Valid CreateUserRequestDto user){
//        userRepository.save(
//                User.builder()
//                        .name(user.getName().trim())
//                        .secondName(user.getSecondname().trim())
//                        .email(user.getEmail().trim())
//                        .birth(user.getBirth())
//                        .build()
//        );
//        System.out.println(user.getName());
//    }

//    @GetMapping("/create")
//    public boolean create(@RequestParam Optional<String> name, @RequestParam Optional<String> email){
//        if(name.isPresent() && email.isPresent()){
//            User u = new User();
//            u.setName(name.get());
//            u.setEmail(email.get());
//            userRepository.save(u);
//            return true;
//        }else {
//            return false;
//        }
//    }
//
//    @GetMapping("/delete")
//    public boolean delete(@RequestParam Optional<Integer> id) {
//        if (id.isPresent()) {
//            if(userRepository.existsById(id.get())) {
//                userRepository.deleteById(id.get());
//                return true;
//            }else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }
//
//    @GetMapping("/update")
//    public boolean update(@RequestParam Optional<Integer> id,@RequestParam Optional<String> name, @RequestParam Optional<String> email){
//        if(id.isPresent() && name.isPresent() && email.isPresent()){
//            if(userRepository.existsById(id.get())) {
//                User oldUser = userRepository.findById(id.get()).get();
//                oldUser.setName(name.get());
//                oldUser.setEmail(email.get());
//                userRepository.save(oldUser);
//                return true;
//            }else {
//                return false;
//            }
//        }else {
//            return false;
//        }
//    }
//
//}
