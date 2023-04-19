package control;

import dao.StudentsDao;
import model.student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static dao.StudentsDao.*;
import static view.view.*;

public class control implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Notification");

        // insert new student in table
        if (e.getSource() == getAdd()) {
            student stu;
            try {
                String name = getName1().getText();
                int age = Integer.parseInt(getAge().getText());
                String address = getAddress().getText();
                double gpa = Double.parseDouble(getGPA().getText());
                stu = new student(name, age, address, gpa);
            }catch (Exception ex){

                JOptionPane.showMessageDialog(frame,
                        "ENTER TRULY THE INFORMATION PLEASE.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }

            try {
                StudentsDao.getInstance().add(stu);
                JOptionPane.showMessageDialog(frame, "ADDED SUCCESSFULLY.");
                setTextInBox();
                loadData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "ERROR ADD A STUDENT.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }

        // edit student information in table
        } else if (e.getSource() == getEdit()) {
            int id;
            try {
               id = Integer.parseInt(getId().getText());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,
                        "ENTER TRULY THE INFORMATION PLEASE.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
            try {
                if(getInstance().checkID(id)){
                    student stu = new student(id);
                    stu = StudentsDao.getInstance().selectById(stu);

                    if(!getName1().getText().equals("")) stu.setName(getName1().getText());
                    if(!getAge().getText().equals("")) stu.setAge(Integer.parseInt(getAge().getText()));
                    if(!getAddress().getText().equals("")) stu.setAddress(getAddress().getText());
                    if(!getGPA().getText().equals("")) stu.setGPA(Double.parseDouble(getGPA().getText()));

                    int result = JOptionPane.showConfirmDialog(frame,
                            "ARE YOU SURE?",
                            "confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if(result == JOptionPane.YES_OPTION){
                        StudentsDao.getInstance().update(stu);
                        getTableModel().getDataVector().removeAllElements();
                        JOptionPane.showMessageDialog(frame, "EDITED SUCCESSFULLY.");
                        setTextInBox();
                        loadData();
                    }

                }else {
                    JOptionPane.showMessageDialog(frame,
                            "ID DOES NOT EXIST.",
                            "error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "ERROR EDIT.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);

                throw new RuntimeException(ex);
            }


            //delete a student in table
        } else if (e.getSource() == getDelete()) {
            int id;
            try {
                id = Integer.parseInt(getId().getText());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,
                        "ENTER TRULY THE INFORMATION PLEASE.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }


            try {
                if(getInstance().checkID(id)){
                    student stu = new student(id);
                    int result = JOptionPane.showConfirmDialog(frame,
                            "ARE YOU SURE?",
                            "confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if(result == JOptionPane.YES_OPTION){
                        StudentsDao.getInstance().delete(stu);
                        JOptionPane.showMessageDialog(frame, "DELETED SUCCESSFULLY.");
                        setTextInBox();
                        loadData();
                    }
                }else {
                    JOptionPane.showMessageDialog(frame,
                            "ID DOES NOT EXIST.",
                            "error",
                            JOptionPane.ERROR_MESSAGE);
                }


            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "ERROR DELETE.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }

            // delete all students in table
        } else if (e.getSource() == getClear()) {
            setTextInBox();

            // sort table students by name
        } else if (e.getSource() == getSortName()) {

            try {
                setTextInBox();
                sortASCByColumName("name");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "ERROR SORT BY NAME.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }

            //sort table students by GPA
        } else if (e.getSource() == getSortGPA()) {

            try {
                setTextInBox();
                sortASCByColumName("GPA");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "ERROR SORT BY GPA.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
            // reset table students
        } else if (e.getSource() == getReset()) {

            try {
                setTextInBox();
                loadData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "ERROR RESET.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        } else if( e.getSource() == getClearTable()){
            try {
                int result = JOptionPane.showConfirmDialog(frame,
                        "ARE YOU SURE?",
                        "confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if(result == JOptionPane.YES_OPTION){
                    StudentsDao.getInstance().deleteALL();
                    JOptionPane.showMessageDialog(frame, "CLEARED SUCCESSFULLY.");
                    setTextInBox();
                    loadData();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "ERROR DELETE ALL.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        }
    }
}

