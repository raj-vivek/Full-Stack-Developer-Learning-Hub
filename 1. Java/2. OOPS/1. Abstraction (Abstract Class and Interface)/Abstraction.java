public class Abstraction {
    public static void main(String[] args) {
        Animal myBird = new Bird();
        Animal myRabbit = new Rabbit();
        Pet petBird = new Bird();
        Pet petRabbit = new Rabbit();

        myBird.makeSound();
        myRabbit.makeSound();

        petBird.play();
        petRabbit.play();
    }
}

abstract class Animal {
    abstract void makeSound();
}

interface Pet{
    void play();
}

class Bird extends Animal implements Pet {

    @Override
    public void play() {
        System.out.println("Bird plays");
    }

    @Override
    void makeSound() {
        System.out.println("Bird chirps");
    }
}

class Rabbit extends Animal implements Pet {

    @Override
    void makeSound() {
        System.out.println("Rabbit plays");
    }

    @Override
    public void play() {
        System.out.println("Rabbit makes sound");
    }
    
}