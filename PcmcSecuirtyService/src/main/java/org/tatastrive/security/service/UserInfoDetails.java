package org.tatastrive.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.tatastrive.security.entity.UserDetails;
import org.tatastrive.security.entity.UserDetails.isActiveStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Data
public class UserInfoDetails  implements org.springframework.security.core.userdetails.UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private String username;
	private String userName;
	@JsonIgnore
    private String password;
	private String primaryMailId;
    private String firstName;
    private String secondName;
    private String isFirstTime;
    private String lastLoginDate;
	private String lastPasswordChangedDate;
	private String primaryContactNo;
    
    public UserInfoDetails(UserDetails userInfo) {
    	userId = userInfo.getUserId();
    	userName = userInfo.getUserName();
    	username = userInfo.getUserName();
        password = userInfo.getPassword();
        firstName = userInfo.getFirstName();
        secondName =userInfo.getSecondName();
        primaryMailId = userInfo.getPrimaryMailId();
        primaryContactNo = userInfo.getPrimaryContactNo();
        isFirstTime = userInfo.getIsFirstTime();
        lastLoginDate = userInfo.getLastLoginDate();
        lastPasswordChangedDate = userInfo.getLastPasswordChangedDate();
        
    }
    
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}
    
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
