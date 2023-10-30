package org.tatastrive.shgdetails.payload.request;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShgDetailsRequestBody {
	public enum status{Active,Deleted}
	
	
	@NotBlank
	@Size(min=1, max=60 , message="Field either balnk or Length is Exceed.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in Shg Name")
	private String shgName;
	
	@NotBlank
	@Size(min=1, max=250 , message="Field either balnk or Length is Exceed.")
	private String addressLine1;
	
	private String addressLine2;
	
	@NotBlank
	@Size(min=1, max=60 , message="Field either balnk or Length is Exceed.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in Villege")
	private String village;
	
	@NotBlank
	@Size(min=1, max=60 , message="Field either balnk or Length is Exceed.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in City")
	private String city;
	
	@NotBlank
	@Size(min=1, max=60 , message="Field either balnk or Length is Exceed.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in District")
	private String district;

	@NotBlank
	@Size(min=1, max=60 , message="Field either balnk or Length is Exceed.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid characters in State")
	private String state;
	
	
	@NotNull(message = "Pincode Should not be blank.")
	@Min(message = "Invalid Pincode", value = 100000)
	@Max(message = "Invalid Pincode", value = 999999)
	private int pincode;
	
	@NotBlank
	@Size(min=1, max=100 , message="Field either balnk or Length is Exceed.")
	@Email(message = "Invalid Email.")
	private String emailId;
	
	@NotBlank
	private String shgFormationDate;
	
	
	@NotNull(message = "Member Count Should not be blank.")
	@Min(message = "Invalid Member Count.", value = 1)
	private int etdNoOfMembers;
	
	@NotBlank
	@Size(min=1, max=45 , message="Field either balnk or Length is Exceed.")
	private String focusArea;
	
	@NotBlank
	@Size(min=1, max=45 , message="Field either balnk or Length is Exceed.")
	private String memberContribution;
	
	@NotBlank
	@Size(min=1, max=45 , message="Field either balnk or Length is Exceed.")
	private String meetingFrequency;
	
	
	@Enumerated(EnumType.STRING)
	private status shgStatus;
	
	@NotNull(message = "User Id Should not be blank.")
	@Min( value = 1 , 	message = "Invalid User Id.")
	private int createdBy;
	
	private int updatedBy;
}
