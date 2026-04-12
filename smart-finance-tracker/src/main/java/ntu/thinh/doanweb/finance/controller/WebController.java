package ntu.thinh.doanweb.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody; // Nhớ thêm dòng này

@Controller
public class WebController {

   
    @GetMapping("/")
    public String home() {
        return "index"; 
    }

   
}