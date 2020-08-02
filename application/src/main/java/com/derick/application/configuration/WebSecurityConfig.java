package com.derick.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private UserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/authenticate").permitAll().
                antMatchers("/").permitAll().
                antMatchers("/api/mpesa/**").permitAll().
                antMatchers("/api/chezanani/**").permitAll().
                antMatchers("/api/signin/**").permitAll().
                antMatchers("/api/signup/**").permitAll().
                antMatchers("/api/confirmOtp/**").permitAll().
                antMatchers("/api/resendOtp/**").permitAll().
                antMatchers("/api/resetpassword/**").permitAll().
                antMatchers("/api/pharmacy/**").permitAll().
                antMatchers("/api/medicine/**").permitAll().
                antMatchers("/api/paymentoption/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY') or hasRole('ROLE_USER')").
                antMatchers("/api/deliverytype/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY') or hasRole('ROLE_USER')").
        antMatchers("/api/medicine/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/prescription/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/prescription/pharmacy/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
                antMatchers("/api/quotation/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
                antMatchers("/api/order/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/medicine/update/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/medicine/add/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/quotation/send/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/paymentoption/option/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/deliverytype/type/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/order/approve/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
        antMatchers("/api/pharmacy/delete/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACY')").
                antMatchers("/home*").permitAll().
                antMatchers("/v2/api-docs").permitAll().
                antMatchers("/swagger-ui.html").permitAll().
                antMatchers("/v2/api-docs", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/**",
                        "/swagger-ui.html", "/webjars/**").permitAll().
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}