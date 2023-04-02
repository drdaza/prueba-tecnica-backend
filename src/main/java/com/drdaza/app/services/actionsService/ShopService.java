package com.drdaza.app.services.actionsService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drdaza.app.exceptions.CourseNotFoundException;
import com.drdaza.app.exceptions.ProfileNotFounException;
import com.drdaza.app.models.Course;
import com.drdaza.app.models.PayMethod;
import com.drdaza.app.models.Profile;
import com.drdaza.app.models.Purchase;
import com.drdaza.app.repository.CourseRepository;
import com.drdaza.app.repository.PayMethodRepository;
import com.drdaza.app.repository.ProfileRepository;
import com.drdaza.app.repository.PurchaseRepository;


@Service
public class ShopService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PayMethodRepository payMethodRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase shopCourse(Long idCourse, Long idPayMethod, Long idProfile) {

        Profile profileDB = profileRepository.findById(idProfile)
                .orElseThrow(() -> new ProfileNotFounException("Course not found"));

        Purchase purchase = createPurchase(idCourse, idPayMethod);

        Set<Course> courses = addCourse(findCourse(idCourse));
        Set<Purchase> purchases = new HashSet<>();

        purchases.add(purchase);

        profileDB.setPurchases(purchases);

        profileDB.setCourses(courses);

        Purchase purchaseDB = purchaseRepository.save(purchase);

        profileRepository.save(profileDB);

        return purchaseDB;
    }

    private Purchase createPurchase(Long idCourse, Long idPayMethod) {

        Course courseDB = findCourse(idCourse);

        PayMethod payMethodDB = payMethodRepository.findById(idPayMethod)
                .orElseThrow(() -> new CourseNotFoundException("PayMethod doesn't exist"));

        return new Purchase(null, courseDB, payMethodDB);
    }

    private Course findCourse(Long idCourse) {
        return courseRepository.findById(idCourse)
                .orElseThrow(() -> new CourseNotFoundException("Course the you try shopp doesn't exist"));
    }

    private Set<Course> addCourse(Course course) {

        Set<Course> courses = new HashSet<>();

        courses.add(course);

        return courses;
    }

}
