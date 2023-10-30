package org.tatastrive.security.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tatastrive.security.repository.UserDetailsRepostory;
import org.tatastrive.security.repository.UserRoleRepository;



@Service
public class UserInfoService implements UserDetailsService {
	
	
	@Autowired
	private UserDetailsRepostory userRepository;
	
	@Autowired
	private UserRoleRepository roleRepository;
	
	 @Autowired
	  private PasswordEncoder encoder;
	 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<org.tatastrive.security.entity.UserDetails> userDetails = userRepository.findByUserName(username);
		 return userDetails.map(UserInfoDetails::new)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

	public String addUser(org.tatastrive.security.entity.UserDetails userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "User Added Successfully";
    }

//	public UserRole getAllUserRoleDetails(Long userId) {
//		// TODO Auto-generated method stub
////		UserRole roleDetails=client.fetchUserRoleDetails(userId);
////		System.out.println("Data From role>>>>"+ roleDetails.toString());
//		UserRole roleDetails= roleRepository.findByUserIdAndIsDefaultAndIsActive(userId,status.Y,status.Y);
//		return roleDetails;
//	}
  
}
