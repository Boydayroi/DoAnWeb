package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.SavingGoal;
import ntu.thinh.doanweb.finance.model.Transaction;
import ntu.thinh.doanweb.finance.repository.SavingGoalRepository;
import ntu.thinh.doanweb.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/savings")
public class SavingGoalController {

    @Autowired
    private SavingGoalRepository savingGoalRepository;

    // Bổ sung kho chứa Giao dịch để ra lệnh trừ tiền
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/all")
    public List<SavingGoal> getAllGoals(Principal principal) {
        return savingGoalRepository.findByUsername(principal.getName());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addGoal(@RequestBody SavingGoal goal, Principal principal) {
        goal.setUsername(principal.getName());
        savingGoalRepository.save(goal);
        return ResponseEntity.ok("Tạo quỹ thành công");
    }

    // ĐÃ NÂNG CẤP: Nạp tiền vào quỹ đồng thời tự động ghi nhận Chi tiêu
    @PostMapping("/add-money/{id}")
    public ResponseEntity<?> addMoney(@PathVariable("id") Long id, @RequestParam("amount") double amount, Principal principal) {
        SavingGoal goal = savingGoalRepository.findById(id).orElse(null);
        
        if (goal != null && goal.getUsername().equals(principal.getName())) {
            // 1. Cộng tiền vào thanh tiến trình của Quỹ
            goal.setCurrentAmount(goal.getCurrentAmount() + amount);
            savingGoalRepository.save(goal);

            // 2. Tự động tạo một giao dịch CHI TIÊU để trừ vào "Tổng số dư"
            Transaction t = new Transaction();
            t.setUsername(principal.getName());
            t.setAmount(amount);
            t.setType("EXPENSE"); // Trừ tiền
            t.setCategory("Tiết kiệm"); // Phân loại vào danh mục Tiết kiệm
            t.setDescription("Nạp tiền vào quỹ: " + goal.getGoalName());
            t.setCreatedAt(LocalDateTime.now());
            
            transactionRepository.save(t); // Lưu giao dịch

            return ResponseEntity.ok("Đã nạp tiền và cập nhật số dư");
        }
        return ResponseEntity.badRequest().body("Lỗi: Không tìm thấy quỹ");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable("id") Long id, Principal principal) {
        SavingGoal goal = savingGoalRepository.findById(id).orElse(null);
        if (goal != null && goal.getUsername().equals(principal.getName())) {
            savingGoalRepository.delete(goal);
            return ResponseEntity.ok("Đã xóa quỹ");
        }
        return ResponseEntity.badRequest().body("Lỗi: Không thể xóa");
    }
}