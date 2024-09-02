import java.util.Objects;

public class ObjectClass {
    public static void main(String[] args) {
        Person p1 = new Person("Vivek", 25);
        Person p2 = new Person("Aditya", 23);

        System.out.println(p1);
        System.out.println(p1.hashCode());
        System.out.println(p2);
        System.out.println(p2.hashCode());

        System.out.println(p1.equals(p2));
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null) return false;

        if(this.getClass() != obj.getClass()) return false;

        Person person = (Person)obj;

        return this.name == person.name && this.age == person.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public String toString() {
        return String.format("Person{name: %1$s, age: %2$s}", name, age);
    }
}