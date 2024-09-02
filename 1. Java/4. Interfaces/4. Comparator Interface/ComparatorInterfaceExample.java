import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorInterfaceExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 85));
        students.add(new Student("Bob", 95));
        students.add(new Student("Charlie", 75));
        students.add(new Student("Amber", 75));

        // Sort students by grade using the GradeComparator
        Collections.sort(students, new GradeComparator());
        // OR
        Comparator<Student> comparator = (s1, s2) -> Integer.compare(s1.grade, s2.grade);
        Collections.sort(students, comparator);
        // OR
        Collections.sort(students, (s1, s2) -> Integer.compare(s1.grade, s2.grade));

        // Print the sorted list of students
        System.out.println(students);

        Collections.sort(students, Comparator.comparing(Student::getName));
        System.out.println(students);

        Collections.sort(students, Comparator.comparing(Student::getGrade).reversed());
        System.out.println(students);
        
        Collections.sort(students, Comparator.comparing(Student::getGrade).reversed().thenComparing(Student::getName));
        System.out.println(students);
    }
}

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', grade=" + grade + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

class GradeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.grade, s2.grade);
    }
}