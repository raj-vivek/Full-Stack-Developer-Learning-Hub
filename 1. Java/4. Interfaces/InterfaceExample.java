public class InterfaceExample {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        myClass.abstractMethod();
        myClass.secondAbstractMethod();
        myClass.defaultMthod();

        MyInterface.staticMethod();

        System.out.println(MyInterface.CONSTANT);
    }
}

interface MyInterface {
    // public static final (by default)
    int CONSTANT = 10;

    void abstractMethod();

    default void defaultMthod() {
        System.out.println("This is a default method");
        privateMethod();
    };

    static void staticMethod() {
        System.out.println("This is a static method");
        privateStaticMethod();
    }

    private void privateMethod() {
        System.out.println("This is a private method");
    }

    private static void privateStaticMethod() {
        System.out.println("This is a private static method");
    }
}

abstract class MyAbstractClass implements MyInterface {
    @Override
    public void abstractMethod() {
        System.out.println("Abstract method implementation in Abstract class");
    }

    public abstract void secondAbstractMethod();
}

class MyClass extends MyAbstractClass {
    @Override
    public void secondAbstractMethod() {
        System.out.println("Second abstract method implementation in concrete class");
    }
}