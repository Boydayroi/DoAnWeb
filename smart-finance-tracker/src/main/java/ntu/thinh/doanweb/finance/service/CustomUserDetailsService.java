package ntu.thinh.doanweb.finance.service;

import ntu.thinh.doanweb.finance.model.User;
import ntu.thinh.doanweb.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Chui vào XAMPP tìm xem có user nào tên này không
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản trong Database");
        }

        // 2. Nếu tìm thấy, báo cho Spring Security biết tên và mật khẩu để nó tự đem đi so sánh
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // Cái mật khẩu {noop}123 sẽ được truyền vào đây
                .roles("USER")
                .build();
    }
}