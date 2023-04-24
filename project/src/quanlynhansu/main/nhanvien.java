package quanlynhansu.main;

import java.util.Objects;

public class nhanvien {
    private int id;
    private String name;
    private String email;
    private int age;
    private Tinh address;
    private int workday;
    private String sex;
    private String part;
    private String position;
    private int salary;

    public nhanvien(int id, String name, String email, int age, Tinh address, int workday, String sex, String part, String position, int salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.workday = workday;
        this.sex = sex;
        this.part = part;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Tinh getAddress() {
        return address;
    }

    public void setAddress(Tinh address) {
        this.address = address;
    }

    public int getWorkday() {
        return workday;
    }

    public void setWorkday(int workday) {
        this.workday = workday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public nhanvien(){
    }

    // save data
    public String toString() {
        return getId() + "," + getName() + "," + getEmail() + "," + getAge() + "," + getAddress() + ","
                + getWorkday() + "," + getSex() + "," + getPart() + "," + getPosition() + "," + getSalary();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        nhanvien other = (nhanvien) obj;
        return sex == other.sex && Objects.equals(email, other.email) && Objects.equals(part, other.part)
                && Objects.equals(position, other.position) && id == other.id && Objects.equals(age, other.age)
                && Objects.equals(address, other.address) && Objects.equals(name, other.name) && age == other.age ;
    }

}
