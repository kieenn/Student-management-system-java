package dao;

import database.JDBCUtil;
import model.student;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static database.JDBCUtil.closeConnection;
import static database.JDBCUtil.getConnection;
import static view.view.getTableModel;

public class StudentsDao implements DaoInterface<student> {
    public static StudentsDao getInstance() {
        return new StudentsDao();
    }

    @Override
    public void add(student student) throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();


        String query = "INSERT INTO students(name, age, address, GPA) " +
                "VALUES ('" + student.getName() + "','" + student.getAge() + "','" + student.getAddress() + "'," + student.getGPA() + ")";

        statement.executeUpdate(query);


        closeConnection(connection);
    }

    @Override
    public void update(student student) throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();


        String query = "UPDATE students\n" +
                "SET\n" +
                "name = '" + student.getName() + "',\n" +
                "age = '" + student.getAge() + "',\n" +
                "address = '" + student.getAddress() + "',\n" +
                "GPA = '" + student.getGPA() + "'\n" +
                "WHERE id ='" + model.student.getID() + "';\n";

        statement.executeUpdate(query);


        closeConnection(connection);
    }

    @Override
    public void delete(student student) throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        String query = "DELETE FROM students\n" +
                "WHERE id ='" + model.student.getID() + "';\n";

        statement.executeUpdate(query);

        closeConnection(connection);
    }

    public void deleteALL() throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        String query = "DELETE FROM students\n";

        statement.executeUpdate(query);


        closeConnection(connection);
    }

    public static void sortASCByColumName(String columName) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM students ORDER BY " + columName + " ASC;";
        ResultSet resultSet = statement.executeQuery(query);
        sortTable(resultSet);
        JDBCUtil.closeConnection(connection);
    }

    @Override
    public ArrayList<student> selectALL() {
        return null;
    }

    @Override
    public student selectById(student student) throws SQLException {
        Statement statement = JDBCUtil.getConnection().createStatement();
        String query = "SELECT * FROM students WHERE id ='" + model.student.getID() + "';";
        ResultSet resultSet = statement.executeQuery(query);


        resultSet.next();
        student.setName(resultSet.getString("name"));
        student.setAge(resultSet.getInt("age"));
        student.setAddress(resultSet.getString("address"));
        student.setGPA(resultSet.getDouble("GPA"));

        JDBCUtil.closeConnection(getConnection());
        return student;
    }

    public Boolean checkID(int id) throws SQLException {

        String query = "SELECT id\n" +
                "FROM students\n" +
                "WHERE EXISTS (SELECT id \n" +
                "              FROM students \n" +
                "              WHERE id = '" + id + "');";
        try {
            Statement statement = getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            resultSet.getString("id");
            JDBCUtil.closeConnection(getConnection());
            return  true;
        }
        catch (SQLException e){
            JDBCUtil.closeConnection(getConnection());
            return false;
        }
    }

    @Override
    public ArrayList<student> selectByCondition(String condition) {
        return null;
    }

    public static void sortTable(ResultSet resultSet) throws SQLException {
        getTableModel().getDataVector().removeAllElements();
        getTableModel().fireTableDataChanged();
        while (resultSet.next()) {
            String[] rows = new String[5];
            rows[0] = resultSet.getString("id");
            rows[1] = resultSet.getString("name");
            rows[2] = resultSet.getString("age");
            rows[3] = resultSet.getString("address");
            rows[4] = resultSet.getString("GPA");
            getTableModel().addRow(rows);

        }
    }
    //show table in mySQL
    public static void loadData() throws SQLException {
        getTableModel().getDataVector().removeAllElements();
        getTableModel().fireTableDataChanged();

        Statement statement = JDBCUtil.getConnection().createStatement();
        String query = "SELECT * FROM students;";
        ResultSet resultSet = statement.executeQuery(query);

        try {
            while (resultSet.next()) {
                String[] rows = new String[5];
                rows[0] = resultSet.getString("id");
                rows[1] = resultSet.getString("name");
                rows[2] = resultSet.getString("age");
                rows[3] = resultSet.getString("address");
                rows[4] = resultSet.getString("GPA");
                getTableModel().addRow(rows);
                closeConnection(getConnection());
            }
        } catch (SQLException e) {
            closeConnection(getConnection());
            JFrame frame = new JFrame("JOptionPane showMessageDialog example");
            JOptionPane.showMessageDialog(frame,
                    "error load data.",
                    "error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
