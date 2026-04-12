package ntu.thinh.doanweb.finance.repository;

import ntu.thinh.doanweb.finance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   
    User findByUsername(String username); 
}