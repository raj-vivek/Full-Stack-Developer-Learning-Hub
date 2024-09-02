import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparableInterfaceExample {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Collections.addAll(list, 
            new Student("Vivek", 25), 
            new Student("Utkarsh", 24)
            );

        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, new StudentNameComparator());
        System.out.println(list);
    }
}

class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student other) {
        return this.age - other.age; // Sort by age
    }

    // Getters and toString() method for display
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}