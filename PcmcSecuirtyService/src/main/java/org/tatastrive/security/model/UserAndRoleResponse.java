package org.tatastrive.security.model;

import org.tatastrive.security.entity.UserRole.status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserAndRoleResponse {
	
	private int userId;
	private String userName ;
	private String firstName;
	private String secondName;
	private String primaryMailId;
	private String primaryContactNo;
	private String isFirstTime;
	private String lastLoginDate;
	private String lastPasswordChangedDate;
	private int loginAttempts;
	
	private int roleId;
	@Enumerated(EnumType.STRING)
	private status isDefault;
	private int entityId;
	private String entityType;
	@Enumerated(EnumType.STRING)
	public status isActive;
}
