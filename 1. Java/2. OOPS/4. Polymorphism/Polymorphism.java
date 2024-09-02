public class Polymorphism {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.makeSound();
        cat.makeSound();
    }
}

class Animal {
    public void makeSound() {
        System.out.println("Lol");
    }
}

class Dog extends Animal {

    @Override
    public void makeSound() {
        System.out.println("Bow Bow");
    }
}

class Cat extends Animal {

    @java.lang.Override
    public void makeSound() {
        System.out.println("Meow");
    }
}