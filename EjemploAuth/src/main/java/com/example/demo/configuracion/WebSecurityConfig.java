package com.example.demo.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Configuración de las reglas de autorización para diferentes rutas
		http.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/admin/**").hasRole("ADMIN") // Rutas bajo "/admin/" requieren el rol ADMIN
				.requestMatchers("/manager/**").hasRole("MANAGER")
				.requestMatchers("/user/**").hasRole("USER") // Rutas bajo "/user/" requieren el rol USER
				.requestMatchers("/login", "/", "/home", "/public/**").permitAll() // Permite el acceso a estas rutas sin autenticación
				.anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
		)
				// Configuración para el proceso de inicio de sesión
				.formLogin(formLogin -> formLogin.loginPage("/login") // URL personalizada de inicio de sesión
				.successHandler(new CustomAuthenticationSuccessHandler()) // Usa el manejador personalizado
				.permitAll())
				// Configuración para el proceso de cierre de sesión
				.logout(logout -> logout.logoutSuccessUrl("/login?logout").invalidateHttpSession(true) // Invalida la sesión actual
				.clearAuthentication(true) // Limpia la autenticación .deleteCookies("JSESSIONID") // Borra la cookie de sesión (opcional)
				.permitAll()

				);

		return http.build();
	}

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
		  UserDetails test = User.builder()
				  .username("test")
				  .password("test")
				  .roles("test")
				  .build();
		  UserDetails admin2 = User.builder()
				  .username("admin2")
				  .password("admin2")
				  .roles("ADMIN")
				  .build();
		 UserDetails manager1 =User.builder()
				 .username("manager1")
				 .password("manager1")
				 .roles("MANAGER")
				 .build();
		 UserDetails manager2 =User.builder()
				 .username("manager2")
				 .password("password2")
				 .roles("MANAGER")
				 .build();
		  
		  

		return new InMemoryUserDetailsManager(user, admin, test, admin2, manager1, manager2);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/**
	 * @Bean public AuthenticationManager authenticationManager( UserDetailsService
	 *       userDetailsService, PasswordEncoder passwordEncoder) {
	 *       DaoAuthenticationProvider authenticationProvider = new
	 *       DaoAuthenticationProvider();
	 *       authenticationProvider.setUserDetailsService(userDetailsService);
	 *       authenticationProvider.setPasswordEncoder(passwordEncoder);
	 * 
	 *       ProviderManager providerManager = new
	 *       ProviderManager(authenticationProvider);
	 *       providerManager.setEraseCredentialsAfterAuthentication(false);
	 * 
	 *       return providerManager; }
	 */

}