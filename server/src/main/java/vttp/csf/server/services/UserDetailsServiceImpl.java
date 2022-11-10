package vttp.csf.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.csf.server.models.User;
import vttp.csf.server.models.UserDetailsImpl;
import vttp.csf.server.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.getUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User is not found with email: " + email));

		return UserDetailsImpl.build(user);
	}

}