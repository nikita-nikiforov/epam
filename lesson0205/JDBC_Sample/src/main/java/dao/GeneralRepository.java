package dao;

import java.sql.SQLException;
import java.util.List;

public interface GeneralRepository <T, ID> {
    List<T> findAll() throws SQLException;

    T findById(ID id) throws SQLException;

    int create(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(ID id) throws SQLException;
}