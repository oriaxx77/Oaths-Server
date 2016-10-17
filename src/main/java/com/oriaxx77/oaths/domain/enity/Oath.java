package com.oriaxx77.oaths.domain.enity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NamedEntityGraph( name="Oath.oathTaker", attributeNodes=@NamedAttributeNode("oathTaker") )
public class Oath
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne( fetch=FetchType.LAZY, optional=false )
	private User oathTaker;
	
	private Long temptationFailedCount;
	
	private Long temptationOvercomeCount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	private String oath;

	public Oath(String oath)
	{
		this.oath = oath;
	}

	public Oath()
	{
		this("");
	}
	
	@PrePersist
	protected void onCreate() 
	{
		created = new Date();
	}

	@PreUpdate
	protected void onUpdate() 
	{
		updated = new Date();
	}
	
}