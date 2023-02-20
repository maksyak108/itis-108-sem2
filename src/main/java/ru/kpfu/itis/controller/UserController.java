package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.CreateUserRequestDto;
import ru.kpfu.itis.dto.UserResponseDto;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{id}")
    public List<UserResponseDto> user(@PathVariable(required = false) Optional<Integer> id){
        if (id.isPresent()) {
            return userRepository.findAllById(List.of(id.get()))
                    .stream()
                    .map(UserResponseDto::fromEntity)
                    .toList();
        } else {
            return userRepository.findAll()
                    .stream()
                    .map(UserResponseDto::fromEntity)
                    .toList();
        }
    }
    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name){
        return String.format("Hello, %s!", name.orElse("Ivan"));
    }

    @PostMapping("/user")
    public void createUser(@Valid @ModelAttribute("user") CreateUserRequestDto user){
        userRepository.save(
                User.builder()
                        .name(user.getName().trim())
                        .secondName(user.getSecondname().trim())
                        .email(user.getEmail().trim())
                        .birth(user.getBirth())
                        .build()
        );
        System.out.println(user.getName());
    }

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

}
