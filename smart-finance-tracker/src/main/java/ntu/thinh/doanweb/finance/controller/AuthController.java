package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.User;
import ntu.thinh.doanweb.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // 1. Mở trang đăng ký
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // 2. Xử lý logic đăng ký người dùng
    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        
        // Kiểm tra xem tên đăng nhập đã tồn tại trong hệ thống chưa
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Tên đăng nhập này đã có người sử dụng!");
            return "register";
        }

        // Khởi tạo đối tượng User mới
        User newUser = new User();
        newUser.setUsername(username);
        
        /* * LƯU Ý: Thêm "{noop}" để Spring Security hiểu đây là mật khẩu thuần, 
         * giúp Thịnh dễ dàng kiểm tra dữ liệu trong XAMPP mà không cần mã hóa phức tạp.
         */
        newUser.setPassword("{noop}" + password); 
        newUser.setEmail(email);
        newUser.setCreatedAt(LocalDateTime.now());

        // Thực hiện lưu dữ liệu vào bảng users trong Database
        userRepository.save(newUser);

        // Sau khi đăng ký thành công, chuyển hướng về trang login kèm thông báo
        return "redirect:/login?registered";
    }
}