package com.example.demo.configuracion;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	       Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

	       // Obtener la sesión actual
	       HttpSession session = request.getSession();
	       
	       if (roles.contains("ROLE_ADMIN")) {
	            response.sendRedirect("/admin/home");
	         // Guardar información en la sesión
	            session.setAttribute("userRole", "ADMIN");
	            
	            
	        } else if (roles.contains("ROLE_USER")) {
	            response.sendRedirect("/user/home");
	            // Guardar información en la sesión
	            session.setAttribute("userRole", "USER");
	            
	            
	        } else if (roles.contains("ROLE_MANAGER")) {
	        	response.sendRedirect("/manager/home");
	            // Guardar información en la sesión
	        	session.setAttribute("userRole", "MANAGER");
	        }
	       
	       /*else {
	            response.sendRedirect("/"); // Redirige a otra página si no es ni USER ni ADMIN
	        }*/
		
	}
}