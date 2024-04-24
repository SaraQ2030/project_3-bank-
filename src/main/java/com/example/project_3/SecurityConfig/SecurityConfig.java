package com.example.project_3.SecurityConfig;
import com.example.project_3.Service.MyUSerDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
   private final MyUSerDetailsService myUSerDetailsService;
@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUSerDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authorizeRequests()
            .requestMatchers("/api/v1/auth/register/customer").permitAll()
            .requestMatchers("/api/v1/auth/register/employee").permitAll()
            .requestMatchers("/api/v1/auth/register" ,"/api/v1/auth/get-user/{username}/{password}","/api/v1/auth/login/{username}/{password}","/api/v1/auth/update/{username}").permitAll()
            .requestMatchers("/api/v1/auth/get-all","/api/v1/auth/delete/{userDel}" ,"/api/v1/employee/update").hasAuthority("ADMIN")  //{username}/
            .requestMatchers("/api/v1/customer/get","api/v1/account/get","api/v1/account/create","api/v1/account/view-account").hasAuthority("CUSTOMER")
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutUrl("/api/v1/auth/logout")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .and()
            .httpBasic();
    return http.build();
}
}
