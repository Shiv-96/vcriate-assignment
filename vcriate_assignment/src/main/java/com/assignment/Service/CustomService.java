package com.assignment.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.assignment.Models.User;
import com.assignment.Repository.UserRepository;

@Service
public class CustomService extends DefaultOAuth2UserService {
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        
        Optional<User> opt = userRepository.findByEmail(email);
        
        if(opt.isEmpty()) {
        	
        	User user = new User();
        	user.setEmail(email);
        	user.setName(oauth2User.getAttribute("name"));
        	userRepository.save(user);
        	
        }

        return new UserService(oauth2User);
    }
	
}
