package ntu.thinh.doanweb.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tắt CSRF để form Đăng ký/Đăng nhập hoạt động
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll() // Mở cửa các trang này
                .anyRequest().authenticated() // Các trang khác (như Dashboard) phải đăng nhập
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") // Nơi Spring tự động nhận dữ liệu username/password
                .defaultSuccessUrl("/", true) // Đăng nhập thành công thì về trang chủ
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout") // Đăng xuất xong về lại trang login
                .permitAll()
            );

        return http.build();
    }
}