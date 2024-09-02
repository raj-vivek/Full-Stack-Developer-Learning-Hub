public class InnerClassExample {

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        
        // Nested Inner Class
        OuterClass.NestedInnerClass memberInnerClass = outerClass.new NestedInnerClass();
        memberInnerClass.display();

        // Static Nested Inner Class
        OuterClass.StaticNestedInnerClass staticNestedClass = new OuterClass.StaticNestedInnerClass();
        staticNestedClass.display();

        // Method Local Inner Class
        outerClass.localInnerClassMethod();

        // Anonymous Inner Class
        outerClass.anonymousInnerClassMethod();
    }
    
}

class OuterClass{
    private String outerField = "Outer Feild";

    public class NestedInnerClass {
        public void display() {
            System.out.println("Accessing outer field: " + outerField);
        }
    }

    static class StaticNestedInnerClass {
        public void display() {
            // Cannot access outerField directly as it's non-static
            System.out.println("Inside static nested class");
        }
    }

    public void localInnerClassMethod() {
        class MethodLocalInnerClass {
            public void display() {
                System.out.println("Inside local inner class, accessing outer field: " + outerField);
            }
        }

        MethodLocalInnerClass localInnerClass = new MethodLocalInnerClass();
        localInnerClass.display();
    }

    // Method with Anonymous Inner Class
    public void anonymousInnerClassMethod() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside anonymous inner class, accessing outer field: " + outerField);
            }
        };
        runnable.run();
    }

}