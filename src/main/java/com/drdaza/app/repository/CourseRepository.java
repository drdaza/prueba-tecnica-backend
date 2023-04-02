package com.drdaza.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drdaza.app.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
