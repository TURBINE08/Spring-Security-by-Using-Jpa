package com.example.demo.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//@Data
@Setter
@Getter
@Entity
@Table(name = "usersrole")
public class User 
{
	@Id
	@Column(name = "uid")
	@GeneratedValue
	private int uid;
	@Column(name = "uname")
	private String name;
	@Column(name = "uemail")
	private String email;
	@Column(name = "pwd")
	private String pwd;
	
	 
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "rolestab", 
	joinColumns = @JoinColumn(name="uid"))
//	@Column(name="role")
	private List<String> roles;
	
}
