package com.assignment.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				authorizeRequests -> authorizeRequests.requestMatchers("/", "/login**", "/error").permitAll().anyRequest().authenticated())
				.oauth2Login(
						oauth2Login -> oauth2Login.loginPage("/login")
								.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
										.oidcUserService(this.oidcUserService()).userService(this.oAuth2UserService())))
				.logout(logout -> logout.logoutSuccessUrl("/"));

		return http.build();

	}

	private DefaultOAuth2UserService oAuth2UserService() {

		DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();

		return new DefaultOAuth2UserService() {

			@Override
			public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

				OAuth2User auth2User = defaultOAuth2UserService.loadUser(userRequest);

				return auth2User;

			}

		};

	}

	private OidcUserService oidcUserService() {

		OidcUserService oidcService = new OidcUserService();

		return new OidcUserService() {
			@Override
			public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
				OidcUser oidcUser = oidcService.loadUser(userRequest);
				// Custom logic to process oidcUser
				return oidcUser;
			}
		};

	}

}
