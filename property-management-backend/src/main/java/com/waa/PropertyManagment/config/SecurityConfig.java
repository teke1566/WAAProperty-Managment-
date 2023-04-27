package com.waa.PropertyManagment.config;


import com.waa.PropertyManagment.enums.Roles;
import com.waa.PropertyManagment.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;



    @Bean
    public UserDetailsService userDetailsSvc() {
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable().cors().and()
                .authorizeHttpRequests()

                .requestMatchers("/api/v1/authenticate/**").permitAll()
//                .requestMatchers(HttpMethod.GET,"/api/v1/products").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.GET,"/api/v1/users").hasAuthority(Roles.ADMIN.name())
                .requestMatchers(HttpMethod.GET,"/api/v1/users/*").hasAnyAuthority(Roles.CUSTOMER.name(), Roles.OWNER.name())

                .requestMatchers(HttpMethod.GET, "/api/v1/properties/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/properties/city/*").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/properties/criteria").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/properties/owner/*/cancelContingency").hasAuthority(Roles.OWNER.name())

                .requestMatchers(HttpMethod.GET, "/api/v1/properties").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/properties/*").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/properties/*").hasAnyAuthority(Roles.CUSTOMER.name(), Roles.OWNER.name())

                .requestMatchers(HttpMethod.POST, "/api/v1/properties/owner").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/properties/owner/*").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.DELETE, "/api/v1/properties/owner/*").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/properties/owner/*/pend").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/properties/owner/*/availabe").hasAuthority(Roles.OWNER.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/properties/owner/*/contigent").hasAuthority(Roles.OWNER.name())


               .requestMatchers(HttpMethod.GET, "/api/v1/customers/*/filter-history").hasAuthority(Roles.CUSTOMER.name())
               .requestMatchers(HttpMethod.GET, "/api/v1/customers/*/active-offers").hasAuthority(Roles.CUSTOMER.name())
               .requestMatchers(HttpMethod.GET, "/api/v1/customers/*/saved-properties").hasAuthority(Roles.CUSTOMER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/customers/saved-properties").hasAuthority(Roles.CUSTOMER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/customers/*/saved-properties").hasAuthority(Roles.CUSTOMER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/customers/*/offers/*/receipt").hasAuthority(Roles.CUSTOMER.name())

                .requestMatchers(HttpMethod.POST, "/api/v1/customers/*/saved-properties").hasAuthority(Roles.ADMIN.name())
                .requestMatchers(HttpMethod.POST, "/api/v1/customers/*/messages").hasAuthority(Roles.CUSTOMER.name())
                .requestMatchers(HttpMethod.POST, "/api/v1/customers/*/properties/*/offers/*/*").hasAuthority(Roles.ADMIN.name())

                .requestMatchers(HttpMethod.GET, "/api/v1/customers").hasAuthority(Roles.CUSTOMER.name())
                .requestMatchers(HttpMethod.POST, "/api/v1/customers/*").hasAuthority(Roles.CUSTOMER.name())
                .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/saved-properties/*").hasAuthority(Roles.CUSTOMER.name())

//                .requestMatchers(HttpMethod.POST, "/api/v1/customers/*").hasAuthority(Roles.ADMIN.name())
//                .requestMatchers(HttpMethod.POST, "/api/v1/customers/*/offers/*/cancel").hasAuthority(Roles.ADMIN.name())
//                .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/*").hasAuthority(Roles.ADMIN.name())


                .requestMatchers("/api/v1/admin/**").hasAuthority(Roles.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsSvc());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
