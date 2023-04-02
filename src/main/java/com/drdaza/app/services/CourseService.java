package com.drdaza.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drdaza.app.exceptions.CourseNotFoundException;
import com.drdaza.app.models.Course;
import com.drdaza.app.repository.CourseRepository;
import com.drdaza.app.services.intefaces.AdminService;
import com.drdaza.app.services.intefaces.BasicService;

@Service
public class CourseService implements AdminService<Course>, BasicService<Course> {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Course courseToDelete = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        courseRepository.delete(courseToDelete);
    }

    @Override
    public Course update(Long id, Course entity) {
        return courseRepository.findById(id)
                .map(courseUpdate -> {
                    courseUpdate.setName(entity.getName());
                    courseUpdate.setPrice(entity.getPrice());
                    return courseRepository.save(courseUpdate);
                }).orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    @Override
    public Course getOne(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    @Override
    public Course Save(Course entity) {
        return courseRepository.save(entity);
    }

}
