package com.deyton.exam.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users") // Specifies the table name in the database
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
	private Long id;

	@NotEmpty(message = "Name is required!") // Ensures the username is not empty
	@Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters") // Sets character limits for the
																					// username
	private String username;

	@NotEmpty(message = "Email is required!") // Ensures the email is not empty
	@Email(message = "Enter a valid email") // Validates the email format
	private String email;

	@NotEmpty(message = "Password is required!") // Ensures the password is not empty
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") // Sets character limits for
																							// the password
	private String password;

	@Transient // Indicates that this field is not to be persisted in the database
	@NotEmpty(message = "Confirm Password is required!") // Ensures the confirm password is not empty
	@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters") // Sets character
																									// limits for the
																									// confirm password
	private String confirm;

	@Column(updatable = false) // Indicates that this field should not be updated after creation
	@DateTimeFormat(pattern = "yyyy-MM-dd") // Formats the date for display
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd") // Formats the date for display
	private Date updatedAt;

	// Automatically sets the createdAt field to the current date when the entity is
	// first persisted
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	// Automatically updates the updatedAt field to the current date whenever the
	// entity is modified
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // One user can have many teams
	private List<Team> teams;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // One user can have many players
	private List<Player> players;

	public User() {
		super();
	}

	// Getter and setter for id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter and setter for username
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// Getter and setter for email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Getter and setter for password
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Getter and setter for confirm
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	// Getter and setter for createdAt
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	// Getter and setter for updatedAt
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	// Getter and setter for teams
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	// Getter and setter for players
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}