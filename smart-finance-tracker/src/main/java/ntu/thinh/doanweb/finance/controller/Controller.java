package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.Transaction;
import ntu.thinh.doanweb.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/")
    public String home(Model model) {
        // Lấy toàn bộ giao dịch từ XAMPP
        List<Transaction> transactions = transactionRepository.findAll();
        
        double totalBalance = 0;
        double totalIncome = 0;
        double totalExpense = 0;

        // Tính toán tổng thu, tổng chi
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

        // Đẩy dữ liệu sang file HTML
        model.addAttribute("transactions", transactions);
        model.addAttribute("totalBalance", totalBalance);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}