package org.tatastrive.shgdetails.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;
@Entity
@Data
public class ShgDetails {
	public enum status{Active,Deleted}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String shgId;
	
	private String shgName;
	private String addressLine1;
	private String addressLine2;
	private String village;
	private String city;
	private String district;
	private String state;
	private int pincode;
	private String emailId;
	private String shgFormationDate;
	private int etdNoOfMembers;
	private String focusArea;
	private String memberContribution;
	private String meetingFrequency;
	@Enumerated(EnumType.STRING)
	private status shgStatus;
	
	private int createdBy;
	private int updatedBy;
}
