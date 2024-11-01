package com.deyton.exam.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "teams") // Specifies the table name in the database
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
	private Long id;

	@NotEmpty(message = "Team name is required!") // Ensures the team name is not empty
	private String name;

	@NotNull(message = "Skill level is required!") // Ensures the skill level is not null
	@Min(value = 1, message = "Skill level must be at least 1!") // Minimum value for skill level
	@Max(value = 5, message = "Skill level must be at most 5!") // Maximum value for skill level
	private Integer skillLevel;

	@NotEmpty(message = "Game day is required!") // Ensures the game day is not empty
	private String gameDay;

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

	// Automatically sets the updatedAt field to the current date when the entity is
	// updated
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	@ManyToOne(fetch = FetchType.LAZY) // Many teams can be associated with one user
	@JoinColumn(name = "user_id") // Foreign key column in the teams table
	private User user;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // One team can have many players
	private List<Player> players;

	public Team() {
		super();
	}

	// Getter and setter for id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter and setter for name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getter and setter for skillLevel
	public Integer getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(Integer skillLevel) {
		this.skillLevel = skillLevel;
	}

	// Getter and setter for gameDay
	public String getGameDay() {
		return gameDay;
	}

	public void setGameDay(String gameDay) {
		this.gameDay = gameDay;
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

	// Getter and setter for user
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Getter and setter for players
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
