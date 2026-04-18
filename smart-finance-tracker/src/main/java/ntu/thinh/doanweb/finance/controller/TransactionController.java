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

    // 1. CHỨC NĂNG THÊM VÀ SỬA (Dùng chung)
    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction, Principal principal) {
        
        // Nếu giao dịch đã có ID (nghĩa là đang Sửa), ta phải giữ nguyên Ngày tạo cũ
        if (transaction.getId() != null) {
            Transaction oldData = transactionRepository.findById(transaction.getId()).orElse(null);
            if (oldData != null) {
                transaction.setCreatedAt(oldData.getCreatedAt());
            }
        } else {
            // Nếu là Thêm mới thì lấy giờ hiện tại
            transaction.setCreatedAt(LocalDateTime.now());
        }
        
        // Gắn tên chủ nhân cho giao dịch
        transaction.setUsername(principal.getName());
        
        // Lưu xuống Database
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Thành công");
    }

    // 2. CHỨC NĂNG XÓA (Đã được vá lỗi Null)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id, Principal principal) {
        
        // Tìm giao dịch trong XAMPP
        Transaction transaction = transactionRepository.findById(id).orElse(null);

        // Đảo ngược chuỗi so sánh (principal.getName().equals...) để chống lỗi rỗng (NullPointerException)
        if (transaction != null && principal.getName().equals(transaction.getUsername())) {
            transactionRepository.delete(transaction);
            return ResponseEntity.ok("Đã xóa thành công");
        }
        
        return ResponseEntity.badRequest().body("Lỗi: Không tìm thấy giao dịch hoặc bạn không có quyền xóa!");
    }
}