package za.ac.cput.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SECURITY!!!");
        http
                .authorizeRequests()
                    //.antMatchers("/", "/home").permitAll()
                    //.antMatcher("**/getall").authenticationProvider(AuthenticationProvider.).hasAnyRole("ADMIN", "MEMBER")
                    .antMatchers(HttpMethod.GET, "**/getall").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.POST, "**/create", "**/update").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.POST, "**/create", "**/update").authenticated()
//                    .antMatchers(HttpMethod.DELETE, "**/delete/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.DELETE, "**/delete/**").authenticated()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("AUTHENTICATION!!!");
        auth.inMemoryAuthentication()
                .withUser("Admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .and()
                .withUser("trainer")
                .password("{noop}trainer123")
                .roles("TRAINER")
                .and()
                .withUser("member")
                .password("{noop}member123")
                .roles("USER");
    }
}
