package ru.sber.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RedirectController {
    @GetMapping("/")
    fun redirectToLoginPage(): String {
        return "redirect:/app/list"
    }
}