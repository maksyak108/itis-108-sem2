package ru.kpfu.itis.controller;

import org.springframework.boot.Banner;
import ru.kpfu.itis.aspect.Loggable;
import ru.kpfu.itis.dto.CreateClientRequestDto;
import ru.kpfu.itis.dto.CreateUserRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/home")
    @Loggable
    public String home(HttpServletRequest httpServletRequest) {
        String currentPrincipalName = httpServletRequest.getUserPrincipal().getName();
        return "home";
    }

    @GetMapping("/")
    @Loggable
    public String index() {
        return "index";
    }

    @GetMapping("/sign_up")
    public String signUp(Model model) {
        model.addAttribute("user", new CreateUserRequestDto());
        return "sign_up";
    }

    @GetMapping("/registerClient")
    @Loggable
    public String registerClient(Model model){
        model.addAttribute("client", new CreateClientRequestDto());
        return "registerClient";
    }

    @GetMapping("/clientSearch")
    @Loggable
    public String clientSearch() {
        return "clientSearch";
    }
}