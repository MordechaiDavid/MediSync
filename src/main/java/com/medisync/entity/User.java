package com.medisync.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")  // מגדיר את שם הטבלה במסד הנתונים
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // יצירת ID אוטומטי
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String role;

    // קונסטרקטור ריק (נדרש ע״י JPA)
    public User() {}

    // קונסטרקטור עם פרמטרים
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
