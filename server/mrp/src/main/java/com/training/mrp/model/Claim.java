package com.training.mrp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Document(collection="claim")
public class Claim {
	
	@Id
	private Integer id;
	
	private String name; 
	private Date dateOfBirth;
	private Date dateOfAdmission;
	private Date dateOfDischarge;
	private String billAmmount;
	
	private Integer memberId;
	private Integer dependentId;
	
}
