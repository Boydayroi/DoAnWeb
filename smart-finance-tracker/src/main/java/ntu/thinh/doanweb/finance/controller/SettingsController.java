package ntu.thinh.doanweb.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String showSettingsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "settings"; // Trỏ tới file settings.html
    }
}