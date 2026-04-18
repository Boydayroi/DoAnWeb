package ntu.thinh.doanweb.finance.controller;

import ntu.thinh.doanweb.finance.model.Transaction;
import ntu.thinh.doanweb.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction) {
        
        transaction.setCreatedAt(LocalDateTime.now());
        
        
        transactionRepository.save(transaction);
        
        return ResponseEntity.ok("Thành công");
    }
}