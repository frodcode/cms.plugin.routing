package routing

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetails

import routing.domain.auth.AuthUser

class AuthService {

	AuthenticationManager authenticationManager

	def login(username, password, request) {
		AuthUser.withNewSession {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authentication = this.authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}
}
