package ntu.thinh.doanweb.finance.repository;

import ntu.thinh.doanweb.finance.model.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByUsername(String username);
}