public class Encapsulation {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Vivek");
        person.setAge(25);
        System.out.println(person.getName());
        System.out.println(person.getAge());
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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
}