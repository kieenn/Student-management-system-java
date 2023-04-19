package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DaoInterface <T>{
    void add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    void deleteALL() throws SQLException;
    ArrayList<T> selectALL() throws SQLException;

    T selectById(T t) throws SQLException;
    ArrayList<T> selectByCondition(String condition);
}
