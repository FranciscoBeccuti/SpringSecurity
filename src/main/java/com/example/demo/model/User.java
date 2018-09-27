package com.example.demo.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;
/**
 * We define an entity, indicating the name of the table, what columns it will have and what its id.
 * Name table = user
 */
@Entity
@Table(name = "user")
public class User {

	/**
	 * The ID variable maps to the user_id column and is auto incremental
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Email(message = "Provide a valid Email")
	@NotEmpty(message = "Provide an email")
	private String email;

	@Length(min = 5, message = "The password must have a minimum of 5 characters")
	@NotEmpty(message = "Provide your password")
	@Transient
	private String password;

	@Length(min = 1, message = "The name must have a minimum of 1 characters")
	@NotEmpty(message = "Provide your name")
	private String name;

	@Length(min = 1, message = "The last name must have a minimum of 1 characters")
	@NotEmpty(message = "Provide your last name")
	private String lastName;

	private int active;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
