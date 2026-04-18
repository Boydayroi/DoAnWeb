package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.Transaction;
import ntu.thinh.doanweb.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction, Principal principal) {
        
        if (transaction.getId() != null) {
            Transaction oldData = transactionRepository.findById(transaction.getId()).orElse(null);
            if (oldData != null) {
                transaction.setCreatedAt(oldData.getCreatedAt());
            } else {
                transaction.setCreatedAt(LocalDateTime.now());
            }
        } else {
            transaction.setCreatedAt(LocalDateTime.now());
        }
        
        transaction.setUsername(principal.getName());
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Thành công");
    }

    // ĐÃ SỬA LỖI Ở ĐÂY: Thêm ("id") vào @PathVariable
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long id, Principal principal) {
        
        Transaction transaction = transactionRepository.findById(id).orElse(null);

        if (transaction == null) {
            System.out.println("❌ Lỗi: Không tìm thấy giao dịch ID = " + id);
            return ResponseEntity.badRequest().body("Không tìm thấy giao dịch");
        }

        System.out.println("=== KIỂM TRA LỆNH XÓA ===");
        System.out.println("Người đang đăng nhập: " + principal.getName());
        System.out.println("Chủ của giao dịch: " + transaction.getUsername());

        if (transaction.getUsername() == null || principal.getName().equals(transaction.getUsername())) {
            transactionRepository.delete(transaction);
            System.out.println("✅ Kết luận: Xóa thành công!");
            return ResponseEntity.ok("Đã xóa");
        }

        System.out.println("⛔ Kết luận: Từ chối xóa vì sai chủ nhân!");
        return ResponseEntity.badRequest().body("Không có quyền xóa giao dịch này");
    }
}