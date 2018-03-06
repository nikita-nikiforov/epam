package dao.impl;

import dao.CourseRepository;
import model.Course;
import persistant.ConnectionManager;
import transformer.Transformer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {
    private static final String FIND_ALL = "SELECT * FROM course";
    private static final String DELETE = "DELETE FROM course WHERE id=?";
    private static final String CREATE = "INSERT course (id, name, description) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE course SET name=?, description=? WHERE " +
            "id=?";
    private static final String FIND_BY_ID = "SELECT * FROM course WHERE id=?";

    @Override
    public List<Course> findAll() throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    courses.add((Course) new Transformer(Course.class)
                            .fromResultSetToEntity(resultSet));
                }
            }
        }
        return courses;
    }

    @Override
    public Course findById(Integer id) throws SQLException {
        Course course = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    course = (Course) new Transformer(Course.class)
                            .fromResultSetToEntity(resultSet);
                }
            }
        }
        return course;
    }

    @Override
    public int create(Course course) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setString(3, course.getDescription());
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int update(Course course) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getId());
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int delete(Integer id) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
    }
}