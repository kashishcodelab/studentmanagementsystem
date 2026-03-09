

import java.util.*;
import java.io.*;

// Student Class
class Student implements Serializable {
    private String name;
    private int rollNo;
    private String grade;

    public Student(String name, int rollNo, String grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void displayStudent() {
        System.out.println("Roll No: " + rollNo + " | Name: " + name + " | Grade: " + grade);
    }
}

// Student Management System Class
class StudentManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "student_database.pdf";

    // Add Student
    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student added successfully.");
    }

    // Remove Student
    public void removeStudent(int rollNo) {
        Student found = null;

        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                found = s;
                break;
            }
        }

        if (found != null) {
            students.remove(found);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search Student
    public void searchStudent(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                s.displayStudent();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Display All Students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student s : students) {
            s.displayStudent();
        }
    }

    // Edit Student
    public void editStudent(int rollNo, String name, String grade) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                s.setName(name);
                s.setGrade(grade);
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Save to File
    public void saveToFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(students);
            out.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    // Load from File
    public void loadFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            students = (ArrayList<Student>) in.readObject();
            in.close();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.loadFromFile();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();

                    if(name.isEmpty() || grade.isEmpty()){
                        System.out.println("Fields cannot be empty!");
                        break;
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    sms.removeStudent(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    sms.searchStudent(sc.nextInt());
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter Roll Number to edit: ");
                    int r = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine();

                    sms.editStudent(r, newName, newGrade);
                    break;

                case 6:
                    sms.saveToFile();
                    System.out.println("Data saved. Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}