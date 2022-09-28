package com.example.zuulgateway.config;

import com.example.common.config.JwtAuthenticationConfig;
import com.example.common.filter.JwtTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationConfig config;

    @Bean
    public JwtAuthenticationConfig jwtConfig() {
        return new JwtAuthenticationConfig();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .anonymous()
                .and()
                .exceptionHandling().authenticationEntryPoint(
                        (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/user").permitAll()
                .antMatchers(config.getUrl()).permitAll()
                .antMatchers("/v1/**").hasRole("API_V1")
                .antMatchers("/v1/api/verify/30-days-address").hasRole("API_V1_VERIFY_ADDRESS_30")
                .antMatchers("/v1/api/verify/7-days-address").hasRole("API_V1_VERIFY_ADDRESS_7")
                .antMatchers("/v1/api/verify/all").hasRole("API_V1_VERIFY_ALL")
                .antMatchers("/v1/api/verify/change-imei").hasRole("API_V1_VERIFY_CHANGE_IMEI")
                .antMatchers("/v1/api/verify/fullname").hasRole("API_V1_VERIFY_FULLNAME")
                .antMatchers("/v1/api/verify/generate-otp").hasRole("API_V1_VERIFY_GEN_OTP")
                .antMatchers("/v1/api/verify/get-consent").hasRole("API_V1_VERIFY_GET_CONSENT")
                .antMatchers("/v1/api/verify/home-address").hasRole("API_V1_VERIFY_HOME")
                .antMatchers("/v1/api/verify/work-address").hasRole("API_V1_VERIFY_WORK")
                .antMatchers("/v1/api/verify/imei-in-12months").hasRole("API_V1_VERIFY_IMEI_12_MONTHS")
                .antMatchers("/v1/api/verify/imei-in-90days").hasRole("API_V1_VERIFY_IMEI_90_DAYS")
                .antMatchers("/v1/api/verify/imei-in-date").hasRole("API_V1_VERIFY_IMEI_IN_DATE")
                .antMatchers("/v1/api/verify/national-id").hasRole("API_V1_VERIFY_ID")
                .antMatchers("/v1/api/verify/number-age").hasRole("API_V1_VERIFY_PHONE_AGE")
                .antMatchers("/v1/api/verify/refer-phone").hasRole("API_V1_VERIFY_REF_PHONE")
                .antMatchers("/v1/api/verify/refer-phone-via-call").hasRole("API_V1_VERIFY_REF_PHONE_CALL")
                .antMatchers("/v1/api/verify/refer-phone-via-time-call").hasRole("API_V1_VERIFY_REF_PHONE_TIME")
                .antMatchers("/v1/api/verify/submit-otp").hasRole("API_V1_VERIFY_SUBMIT_OTP")
                .antMatchers("/v1/api/report/request").hasRole("API_V1_REPORT_REQUEST")
                .anyRequest().permitAll();
    }


}
