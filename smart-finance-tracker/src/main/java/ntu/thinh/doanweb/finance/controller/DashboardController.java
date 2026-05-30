package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.Transaction;
import ntu.thinh.doanweb.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private TransactionRepository transactionRepo;

    // CHỈ GIỮ LẠI HÀM TẢI GIAO DIỆN TRANG CHỦ & TÍNH TOÁN SỐ LIỆU
    @GetMapping("/")
    public String showDashboard(Model model, Principal principal) {
        // Lấy tên người dùng
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }

        // Lấy toàn bộ giao dịch
        List<Transaction> transactions = transactionRepo.findAll();
        model.addAttribute("transactions", transactions);

        // Tính toán Tổng thu, Tổng chi và Số dư
        double totalIncome = 0;
        double totalExpense = 0;
        for (Transaction t : transactions) {
            if (t.getAmount() != null) {
                if ("INCOME".equals(t.getType())) {
                    totalIncome += t.getAmount();
                } else if ("EXPENSE".equals(t.getType())) {
                    totalExpense += t.getAmount();
                }
            }
        }
        
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("totalBalance", totalIncome - totalExpense);

        return "index";
    }
}