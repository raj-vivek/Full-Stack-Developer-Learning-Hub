public class NestedInterfaceExample {
    public static void main(String[] args) {
        OuterClass.ClassNestedInterface instance1 = new ImplementationClass();
        instance1.display();
    
        OuterInterface.InnerInterface instance2 = new AnotherImplementationClass();
        instance2.show();
    }
}

class OuterClass {
    // implicitly static
    interface ClassNestedInterface {
        void display();
    }
}

class ImplementationClass implements OuterClass.ClassNestedInterface {
    @Override
    public void display() {
        System.out.println("Implementation of nested interface method.");
    }
}

interface OuterInterface {
    // implicitly public static
    interface InnerInterface {
        void show();
    }
}

class AnotherImplementationClass implements OuterInterface.InnerInterface {
    @Override
    public void show() {
        System.out.println("Implementation of nested interface method.");
    }
}