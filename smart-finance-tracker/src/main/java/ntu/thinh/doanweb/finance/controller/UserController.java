package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.User;
import ntu.thinh.doanweb.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> payload, Principal principal) {
        String newPassword = payload.get("newPassword");

        if (newPassword == null || newPassword.length() < 6) {
            return ResponseEntity.badRequest().body("Mật khẩu quá ngắn");
        }

        User user = userRepository.findByUsername(principal.getName());
        
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok("Đổi mật khẩu thành công");
        }

        return ResponseEntity.badRequest().body("Lỗi: Không tìm thấy tài khoản");
    }
}