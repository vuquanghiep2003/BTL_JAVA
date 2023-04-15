import java.util.ArrayList;
import java.util.Scanner;

// Lớp đại diện cho đối tượng nhân viên
public class Employee {
    private String id;
    private String name;
    private int age;

    public Employee(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Các phương thức getter và setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

// Lớp quản lý nhân viên
class EmployeeManager {
    private ArrayList<Employee> employeeList = new ArrayList<>();

    // Thêm nhân viên mới
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    // Sửa thông tin nhân viên
    public void editEmployee(String id, String newName, int newAge) {
        for (Employee employee : employeeList) {
            if (employee.getId().equals(id)) {
                employee.setName(newName);
                employee.setAge(newAge);
                break;
            }
        }
    }

    // Xóa nhân viên
    public void deleteEmployee(String id) {
        for (Employee employee : employeeList) {
            if (employee.getId().equals(id)) {
                employeeList.remove(employee);
                break;
            }
        }
    }

    // Hiển thị danh sách nhân viên
    public void displayEmployees() {
        System.out.println("Employee List:");
        for (Employee employee : employeeList) {
            System.out.println(employee.toString());
        }
    }
}
