package com.drdaza.app.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profiles")
    private Long id;
    private String experience;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "courses_profiles", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<Course> courses;

    @OneToMany
    private Set<Purchase> purchases;

    public Profile() {
        this.courses = new HashSet<>();
        this.purchases = new HashSet<>();
    }

    public Profile(Long id, String experience) {
        this.id = id;
        this.experience = experience;
        this.courses = new HashSet<>();
        this.purchases = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

}
