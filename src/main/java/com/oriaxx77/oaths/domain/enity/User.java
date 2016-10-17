package com.oriaxx77.oaths.domain.enity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
public class User 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String name;

	@Column(unique = true)
	private String authCode;
	private String authToken;

	private String pushNotifictionDeviceToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	public User(String email, String name, String authCode, String authToken, String pushNotifictionDeviceToken) 
	{
		this.email = email;
		this.name = name;
		this.authCode = authCode;
		this.authToken = authToken;
		this.pushNotifictionDeviceToken = pushNotifictionDeviceToken;
	}

	public User() 
	{
		this("", "", "", "", "");
	}
}