package com.drdaza.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "purchases")
public class Purchase {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_purchase")
    private Long id;

    @OneToOne
    private Course course;

    @OneToOne
    private PayMethod payMethod;

    public Purchase(Long id, Course course, PayMethod payMethod) {
        this.id = id;
        this.course = course;
        this.payMethod = payMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }
    
    
}
