package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.User;
import ntu.thinh.doanweb.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FixController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/fix-login")
    public String fixLogin() {
        // Tìm tài khoản thinhntu trong database
        User user = userRepository.findByUsername("thinhntu");
        if (user != null) {
            // Nhờ chính Spring Boot mã hóa mật khẩu 123456
            user.setPassword(passwordEncoder.encode("123456"));
            // Lưu lại vào MySQL
            userRepository.save(user);
            return "✅ ĐÃ SỬA LỖI MẬT KHẨU THÀNH CÔNG! <br><br> Bạn hãy quay lại trang <a href='/login'>http://localhost:8081/login</a> và đăng nhập với tài khoản: <b>thinhntu</b> / mật khẩu: <b>123456</b>";
        }
        return "❌ Không tìm thấy tài khoản thinhntu trong DB. Bạn hãy kiểm tra lại phpMyAdmin xem đã có tài khoản này chưa nhé.";
    }
}