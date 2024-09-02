public class MarkerInterfaceExample {
    public static void main(String[] args) {
        User user = new User("john_doe", "password123");

        if (user instanceof Auditable) {
            System.out.println("This object is auditable.");
        } else {
            System.out.println("This object is not auditable.");
        }
    }
}

interface Auditable {
    // Marker interface
}

class User implements Auditable {
    private String username;
    private String password;
    
    // Constructors, getters, setters, etc.
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}