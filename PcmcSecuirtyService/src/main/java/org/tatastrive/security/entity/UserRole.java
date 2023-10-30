package org.tatastrive.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_role_map")
public class UserRole {
	public enum status {Y,N};
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private int userId;
	private int roleId;
	@Enumerated(EnumType.STRING)
	private status isDefault;
	private int entityId;
	private String entityType;
	@Enumerated(EnumType.STRING)
	public status isActive;
	private int createdBy;
	private int updated_by;
	
}
