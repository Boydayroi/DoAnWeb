package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.Transaction;
import ntu.thinh.doanweb.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

// Khai báo đường dẫn tuyệt đối để không bị nhầm lẫn với tên file Controller của bạn
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        // 1. Lấy tên tài khoản đang đăng nhập từ hệ thống bảo mật
        String currentUsername = principal.getName();
        
        // 2. Chỉ lấy danh sách giao dịch thuộc về người dùng này
        List<Transaction> transactions = transactionRepository.findByUsername(currentUsername);
        
        double totalBalance = 0;
        double totalIncome = 0;
        double totalExpense = 0;

        // 3. Tính toán các con số thống kê dựa trên dữ liệu riêng tư
        for (Transaction t : transactions) {
            if (t.getAmount() != null) {
                if ("INCOME".equals(t.getType())) {
                    totalIncome += t.getAmount();
                    totalBalance += t.getAmount();
                } else if ("EXPENSE".equals(t.getType())) {
                    totalExpense += t.getAmount();
                    totalBalance -= t.getAmount();
                }
            }
        }

        // 4. Gửi dữ liệu đã lọc sang giao diện index.html
        model.addAttribute("transactions", transactions);
        model.addAttribute("totalBalance", totalBalance);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("username", currentUsername); // Gửi tên để hiện lên Header nếu cần
        
        return "index";
    }
}