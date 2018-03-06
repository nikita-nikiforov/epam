package service;

import dao.impl.CourseRepositoryImpl;
import model.Course;
import java.sql.SQLException;
import java.util.List;

public class CourseService {
    public List<Course> findAll() throws SQLException {
        return new CourseRepositoryImpl().findAll();
    }

    public Course findById(int id) throws SQLException {
        return new CourseRepositoryImpl().findById(id);
    }

    public int create(Course course) throws SQLException {
        return new CourseRepositoryImpl().create(course);
    }

    public int delete(int id) throws SQLException {
        return new CourseRepositoryImpl().delete(id);
    }

    public int update(Course course) throws SQLException {
        return new CourseRepositoryImpl().update(course);
    }
}