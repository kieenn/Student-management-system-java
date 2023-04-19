package view;

import control.control;
import control.tableEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

import static dao.StudentsDao.loadData;


public class view extends  JFrame{

    private static JTextField id;
    private static JTextField name1;
    private static JTextField age;
    private static JTextArea address;
    private static JTextField GPA;

    private static JButton add, edit, delete, clear, sortGPA, sortName, reset, clearTable;

    private static JTable tableStudent;
    private static final DefaultTableModel tableModel = new DefaultTableModel();


    public view() throws SQLException {
        setSize(1100,600);
        build();
        loadData();
        setVisible(true);
    }


    public void build(){
        setLooknFeel();
        JPanel panel1 = new JPanel(new GridLayout(5,2,0, 20));
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 =  new JPanel(new GridLayout(1,1));

        //label
        JLabel ID = new JLabel("ID");
        ID.setFont(ID.getFont().deriveFont(Font.BOLD));
        JLabel name = new JLabel("Name");
        name.setFont(name.getFont().deriveFont(Font.BOLD));
        JLabel age1 = new JLabel("Age");
        age1.setFont(age1.getFont().deriveFont(Font.BOLD));
        JLabel address1 = new JLabel("Address");
        address1.setFont(address1.getFont().deriveFont(Font.BOLD));
        JLabel gpa = new JLabel("GPA");
        gpa.setFont(gpa.getFont().deriveFont(Font.BOLD));

        //Text input

        id = new JTextField(5);
//        id.setEditable(false);
        name1 = new JTextField(20);
        age = new JTextField(5);
        address = new JTextArea();
        GPA = new JTextField(5);

        //button

        add = new JButton("Add");
        add.addActionListener(new control());
        delete = new JButton("Delete");
        delete.addActionListener(new control());
        edit = new JButton("Edit");
        edit.addActionListener(new control());
        clear = new JButton("Clear");
        clear.addActionListener(new control());
        sortGPA = new JButton("Sort By GPA");
        sortGPA.addActionListener(new control());
        sortName = new JButton("Sort By Name");
        sortName.addActionListener(new control());
        reset = new JButton("Reset");
        reset.addActionListener(new control());
        clearTable = new JButton("Clear Table");
        clearTable.addActionListener(new control());

        //table
        String[] columName = {"ID", "Name", "AGE", "ADDRESS", "GPA"};

        tableStudent = new JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columName);
        JScrollPane scrollPane = new JScrollPane(tableStudent);
        tableStudent.setModel(tableModel);
        tableStudent.addMouseListener(new tableEvent());


        //add into panel

        panel1.add(ID);
        panel1.add(id);
        panel1.add(name);
        panel1.add(name1);
        panel1.add(age1);
        panel1.add(age);
        panel1.add(address1);
        panel1.add(address);
        panel1.add(gpa);
        panel1.add(GPA);

        panel2.add(add);
        panel2.add(edit);
        panel2.add(delete);
        panel2.add(clear);

        panel3.add(scrollPane);
        panel3.add(sortGPA);
        panel3.add(sortName);
        panel3.add(reset);
        panel3.add(clearTable);

        panel4.add(panel1);
        panel4.add(panel2);

        panel5.add(panel4);
        panel5.add(panel3);

        add(panel5);

    }

    private void setLooknFeel() {
        //set Look n Feels
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch (Exception e) {
            System.out.println("Look and Feel is not set!");
        }
    }
    public static void setTextInBox(){
        getId().setText("");
        getName1().setText("");
        getAge().setText("");
        getAddress().setText("");
        getGPA().setText("");
    }

    public static JTextField getId() {
        return id;
    }

    public static JTextField getName1() {
        return name1;
    }

    public static JTextField getAge() {
        return age;
    }

    public static JTextArea getAddress() {
        return address;
    }

    public static JTextField getGPA() {
        return GPA;
    }

    public static JButton getAdd() {
        return add;
    }

    public static JButton getEdit() {
        return edit;
    }

    public static JButton getDelete() {
        return delete;
    }

    public static JButton getClear() {
        return clear;
    }

    public static JButton getSortGPA() {
        return sortGPA;
    }

    public static JButton getSortName() {
        return sortName;
    }

    public static JTable getTableStudent() {
        return tableStudent;
    }

    public static DefaultTableModel getTableModel() {
        return tableModel;
    }

    public static JButton getReset() {
        return reset;
    }
    public static JButton getClearTable(){
        return clearTable;
    }


}
