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

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.badRequest().body("Lỗi");
    }

    @PostMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody Map<String, String> data, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        if (user != null) {
            user.setFullName(data.get("fullName"));
            user.setPhone(data.get("phone"));
            user.setEmail(data.get("email"));
            if (data.get("avatar") != null) user.setAvatar(data.get("avatar"));
            userRepository.save(user);
            return ResponseEntity.ok("Thành công");
        }
        return ResponseEntity.badRequest().body("Lỗi");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> payload, Principal principal) {
        String newPass = payload.get("newPassword");
        User user = userRepository.findByUsername(principal.getName());
        if (user != null && newPass != null && newPass.length() >= 6) {
            user.setPassword(passwordEncoder.encode(newPass));
            userRepository.save(user);
            return ResponseEntity.ok("Xong");
        }
        return ResponseEntity.badRequest().body("Lỗi mật khẩu");
    }
}