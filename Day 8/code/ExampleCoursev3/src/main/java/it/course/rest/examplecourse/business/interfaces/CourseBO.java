package it.course.rest.examplecourse.business.interfaces;

import it.course.rest.examplecourse.model.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface CourseBO  {
     public Course findByIdFile(Long id);
     void uploadFile(Long id, MultipartFile data) throws IOException;
     public Course createCourseUser(Long id , Course course);

     Course findCourse(Long id );
     void deleteById(Long id );

     ArrayList<Course> findAll();

     void save(Course course);
}
