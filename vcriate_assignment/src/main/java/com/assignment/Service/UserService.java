package com.assignment.Service;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserService implements OAuth2User, UserDetails {
	
	private OAuth2User auth2User;
	
	public UserService(OAuth2User auth2User) {
        this.auth2User = auth2User;
    }
	
	
	@Override
	public Map<String, Object> getAttributes() {
		return auth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return auth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return auth2User.getAttribute("name");
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUsername();
	}

}
