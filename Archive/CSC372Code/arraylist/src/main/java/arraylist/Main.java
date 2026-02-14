package arraylist;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static class Student {
        int rollno;
        String name;
        String address;

    public Student(int rollno, String name, String address) {
        this.rollno = rollno;
        this.name = name;
        this.address = address;
    }
    private void displayInfo() {
        System.out.println("Roll No: " + rollno + ", Name: " + name + ", Address: " + address);
    }
}



public static void main(String[] args) {
        Student student1 = new Student(101, "Hammond, John", "India");
        Student student2 = new Student(102, "Olay, Ravi", "India");
        Student student3 = new Student(103, "Sellamat, Hanumat", "India");
        Student student4 = new Student(104, "Dough, John", "USA");
        Student student5 = new Student(105, "Angel, Miguel", "Spain");
        Student student6 = new Student(106, "Wong, Lee", "China");
        Student student7 = new Student(107, "Meier, Hans", "Germany");
        Student student8 = new Student(108, "Petrov, Ivan", "Russia");
        Student student9 = new Student(109, "Silva, Carlos", "Brazil");
        Student student10 = new Student(110, "Khan, Ahmed", "Egypt");

        ArrayList<Student> classroom372 = new ArrayList<Student>();
        classroom372.add(student1);
        classroom372.add(student2);
        classroom372.add(student3);
        classroom372.add(student4);
        classroom372.add(student5);
        classroom372.add(student6);
        classroom372.add(student7);
        classroom372.add(student8);
        classroom372.add(student9);
        classroom372.add(student10);

        System.out.println("classroom372 before sorting:\n");
        for (Student student : classroom372) {
            student.displayInfo();
        }

        System.out.println("\nSorting by name:\n");
        SelectionSort.mySort(classroom372, new sortbyName());
        for (Student student : classroom372) {
            student.displayInfo();
        }

        System.out.println("\nSorting by roll number:\n");
        SelectionSort.mySort(classroom372, new sortbyRollno());
        for (Student student : classroom372) {
            student.displayInfo();
        }
    }
}
