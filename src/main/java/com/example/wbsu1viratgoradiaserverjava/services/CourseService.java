package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.Course;
import com.example.wbsu1viratgoradiaserverjava.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/courses")
    public List<Course> findAllCourses(){
        return (List<Course>) courseRepository.findAll();
    }

    @GetMapping("/api/courses/{courseId}")
    public Optional<Course> findCourseById(@PathVariable("courseId") int courseId) {
        return courseRepository.findById(courseId);
    }

    @PostMapping("api/courses")
    public Course addCourse(@RequestBody Course newCourse) {
        courseRepository.save(newCourse);
        return newCourse;
    }

    @PutMapping("api/courses/{courseId}")
    public void updateCourse(@PathVariable("courseId") int courseId,
                             @RequestBody Course newCourse) {
        Optional<Course> c = courseRepository.findById(courseId);
        if(c.isPresent()) {
            c.get().setTitle(newCourse.getTitle());
            c.get().setModules(newCourse.getModules());
            courseRepository.save(c.get());
        }
    }

    @DeleteMapping("/api/courses/{courseId}")
    public void deleteCourse(@PathVariable("courseId") int courseId) {
        courseRepository.deleteById(courseId);
    }
}
