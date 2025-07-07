
package com.example.video_voting.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.video_voting.model.supporting.Role;
import com.google.gson.annotations.Expose;

import jakarta.persistence.*;

/**
 * User
 */
@Entity
@Table(name = "users")
public class User {

  @Expose
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Expose
  @Column(nullable = false)
  private String firstname;

  @Expose
  @Column(nullable = false)
  private String lastname;

  @Expose
  @Column(nullable = false, unique = true)
  private String username;

  @Expose
  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Expose
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Set<Role> roles = new HashSet<>();

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  // Constructors

  public User() {
  }

  public User(String firstname, String lastname, String username, String email, String password, Set<Role> roles) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
