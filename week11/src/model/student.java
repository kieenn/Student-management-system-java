package model;

public class student {
    private static int ID;
    private String name;
    private int age;
    private String address;
    private double GPA;

    public student(int id) {
        ID = id;
    }

    public student(String name, int age, String address, double GPA) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.GPA = GPA;
    }

    public static int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public double getGPA() {
        return GPA;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
}
