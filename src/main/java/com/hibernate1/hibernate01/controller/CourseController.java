package com.hibernate1.hibernate01.controller;

import com.hibernate1.hibernate01.entity.Course;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    List<Course> courseList = new ArrayList<>();
    Course course;
    public Course findById(int id){
        for (Course course:
             courseList) {
            if(course.getId() == id){
                return course;
            }
        }
        return null;
    }
    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses(){
        return courseList;
    }
    @PostMapping("/add")
    public String createCourse(@RequestBody Course course){
        courseList.add(course);
        return "Success";
    }
    @PutMapping("/update/{id}")
    public String updateCourse(@RequestBody Course newCourse,@PathVariable int id){
        Course courseUpdate = courseList.get(courseList.indexOf(findById(id)));
        courseUpdate.setId(newCourse.getId());
        courseUpdate.setName(newCourse.getName());
        courseUpdate.setAuthor(newCourse.getAuthor());
        return "Updated";
    }
    @DeleteMapping("/delete/{id}")
    public String removeCourse(@PathVariable int id){
        courseList.remove(courseList.indexOf(findById(id)));
        return "deleted";
    }
}
