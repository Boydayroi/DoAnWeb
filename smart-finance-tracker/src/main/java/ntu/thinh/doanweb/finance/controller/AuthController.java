package ntu.thinh.doanweb.finance.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ntu.thinh.doanweb.finance.model.User;
import ntu.thinh.doanweb.finance.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Tên đăng nhập này đã có người sử dụng!");
            return "register";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        
        // Sử dụng PasswordEncoder đã cấu hình để mã hóa mật khẩu
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setCreatedAt(LocalDateTime.now());

        userRepository.save(newUser);

        return "redirect:/login?registered";
    }
}