package org.tatastrive.memberdetails.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class MemberDetails {
	public enum requiredTraining {Y,N}
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String aadharNumber;
	
	@NotBlank
	@Size(min=1, max=60 , message="Field either balnk or Length is Exceed.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in Member First Name")
	private String fiirstName;
	
	private String middleName;
	
	@NotBlank
	@Size(min=1, max=60 , message="Field either balnk or Length is Exceed.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in Member Last Name")
	private String lastName;
	
	@NotBlank
	@Pattern(regexp = "\\\\d{4}-\\\\d{2}-\\\\d{2}" , message = "Dob format is Invalid.")
	private String dob;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	private String maritalStatus;
	
	@NotBlank
	@Size(min = 10,max = 15, message = "Invalid Contact Number.")
	@Pattern(regexp = "\"^[56789]\\\\d{11}$\"",message = "Contact Formate is Invalid.")
	private String primaryContactNumber;
	
	@NotBlank
	@Size(min=1, max=100 , message="Field either balnk or Length is Exceed.")
	@Email(message = "Invalid Email.")
	private String emailAddress;
	
	@NotBlank
	private String highestEducation;
	
	@NotNull
	@Min(value = 9999, message = "Invalid Passing Year." )
	@Pattern(regexp = "^[0-9]{4}$", message = "Not in Proper Formate.")
	private int passingYear;
	
	@NotBlank
	private String occupation;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]+$", message = "Invalid Income Details.")
	private String monthlyIncome;
	
	@NotBlank
	private String incomeSource;
	
	@NotBlank
	private String bankName;
	
	@NotBlank
	private String bankAccountNumber;
	
	@NotBlank
	private String socialCategory;
	
	@NotNull
	private int noOfDependents;
	
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in Member Father Name")
	private String fatherName;
	
	@NotBlank
	private String fatherOccupation;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in Member Mother Name")
	private String motherName;
	
	@NotBlank
	private String motherOccupation;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in Member Spouse Name")
	private String spouseName;
	
	@NotBlank
	private String spouseOccupation;
	
	@NotBlank
	private String interestAreas;
	
	@NotBlank
	private String anySkillTrainingsAttended;
	
	@NotBlank
	private String trainedAreas;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private requiredTraining trainingRequirements;
	
	@NotBlank
	private String trainingRequiredAreas;
	
	@NotNull
	@Min(value = 1, message = "User Id Can't be Blank.")
	private int createdBy;
	
	private int updatedBy;
	
	private String remarks;
}
