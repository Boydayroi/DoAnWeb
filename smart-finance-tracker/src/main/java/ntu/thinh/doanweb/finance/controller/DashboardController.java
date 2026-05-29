package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.Transaction;
import ntu.thinh.doanweb.finance.model.SavingGoal;
import ntu.thinh.doanweb.finance.repository.TransactionRepository;
import ntu.thinh.doanweb.finance.repository.SavingGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private SavingGoalRepository savingRepo;

    // =======================================================
    // 1. TẢI GIAO DIỆN TRANG CHỦ & TÍNH TOÁN SỐ LIỆU
    // =======================================================
    @GetMapping("/")
    public String showDashboard(Model model, Principal principal) {
        // Lấy tên người dùng hiển thị góc trái
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }

        // Lấy toàn bộ giao dịch từ MySQL
        List<Transaction> transactions = transactionRepo.findAll();
        model.addAttribute("transactions", transactions);

        // Thuật toán tính Tổng thu, Tổng chi và Số dư
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
        
        // Truyền số liệu ra ngoài giao diện HTML
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("totalBalance", totalIncome - totalExpense);

        return "index"; // Mở file index.html
    }

    // =======================================================
    // 2. CÁC HÀM XỬ LÝ API (THÊM, SỬA, XÓA DỮ LIỆU TỪ WEB)
    // =======================================================

    // API Thêm Giao dịch
    @PostMapping("/api/transactions/add")
    @ResponseBody
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction) {
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepo.save(transaction);
        return ResponseEntity.ok().build();
    }

    // API Xóa Giao dịch
    @DeleteMapping("/api/transactions/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // API Lấy danh sách Quỹ tiết kiệm để vẽ thanh %
    @GetMapping("/api/savings/all")
    @ResponseBody
    public ResponseEntity<?> getAllSavings() {
        return ResponseEntity.ok(savingRepo.findAll());
    }

    // API Thêm Quỹ tiết kiệm
    @PostMapping("/api/savings/add")
    @ResponseBody
    public ResponseEntity<?> addSaving(@RequestBody SavingGoal savingGoal) {
        savingGoal.setCurrentAmount(0.0); // Mới tạo thì số tiền hiện có bằng 0
        savingRepo.save(savingGoal);
        return ResponseEntity.ok().build();
    }

    // API Nạp tiền vào Quỹ
    @PostMapping("/api/savings/add-money/{id}")
    @ResponseBody
    public ResponseEntity<?> addMoneyToGoal(@PathVariable Long id, @RequestParam double amount) {
        SavingGoal goal = savingRepo.findById(id).orElse(null);
        if (goal != null) {
            goal.setCurrentAmount(goal.getCurrentAmount() + amount);
            savingRepo.save(goal);
        }
        return ResponseEntity.ok().build();
    }

    // API Xóa Quỹ
    @DeleteMapping("/api/savings/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteGoal(@PathVariable Long id) {
        savingRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}