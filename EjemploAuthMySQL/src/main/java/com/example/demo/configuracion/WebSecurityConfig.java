package com.example.demo.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.servicio.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	 @Autowired
	 private CustomUserDetailsService userDetailsService;
	 
	@Bean
	public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Configuración de las reglas de autorización para diferentes rutas
		   http.authorizeHttpRequests((authorize) -> authorize
		       .requestMatchers("/admin/**").hasRole("ADMIN")  // Rutas bajo "/admin/" requieren el rol ADMIN
		       .requestMatchers("/user/**").hasRole("USER")    // Rutas bajo "/user/" requieren el rol USER
		       .requestMatchers("/login","/", "/home", "/public/**").permitAll() // Permite el acceso a estas rutas sin autenticación
		       .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
	        
	        ).userDetailsService(userDetailsService)
		   // Configuración para el proceso de inicio de sesión
	       .formLogin(formLogin -> 
            formLogin
                .loginPage("/login")  // URL personalizada de inicio de sesión
                .successHandler(new CustomAuthenticationSuccessHandler())  // Usa el manejador personalizado                        
                .permitAll())
	       // Configuración para el proceso de cierre de sesión
	        .logout(logout -> 
            logout.logoutSuccessUrl("/login?logout")
            .invalidateHttpSession(true)       // Invalida la sesión actual
            .clearAuthentication(true)        // Limpia la autenticación
         //   .deleteCookies("JSESSIONID")      // Borra la cookie de sesión (opcional)
            .permitAll() 

        );
	      
	    return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	/**
	 * En memoria
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
		        .username("user")
		        .password(passwordEncoder().encode("user"))
		        .roles("USER") // Roles se especifican aquí
		        .build();

		    UserDetails admin = User.builder()
		        .username("admin")
		        .password(passwordEncoder().encode("admin"))
		        .roles("ADMIN") // Roles se especifican aquí
		        .build();

		return new InMemoryUserDetailsManager(user, admin);
	}*/
	




}