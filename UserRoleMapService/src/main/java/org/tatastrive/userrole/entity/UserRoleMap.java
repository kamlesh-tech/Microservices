package org.tatastrive.userrole.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserRoleMap {
	
	public enum status {Y,N};
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int userId;
	private int  roleId;
	@Enumerated(EnumType.STRING)
	private status isDefault;
	private int  entityId;
	
	private String entityType;
	@Enumerated(EnumType.STRING)
	private status isActive;
	private int createdBy;
	private int updatedBy;
}
