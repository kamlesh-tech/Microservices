package org.tatastrive.security.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;
import java.util.List;


@Data
@Entity
public class UserDetails {
	public enum isActiveStatus{Y,N}
;	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long userId;
	private String userName ;
	private String firstName;
	private String secondName;
	private String primaryMailId;
	private String primaryContactNo;
	private String isFirstTime;
	@Enumerated(EnumType.STRING)
	private isActiveStatus isActive;
	private String lastLoginDate;
	private String lastPasswordChangedDate;
	private int loginAttempts;
	private String password ;
}
