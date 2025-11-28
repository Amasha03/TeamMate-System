package main;
public abstract class User {
    String id;
    String email;

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    //MENU FOR PARTICIPANTS AND ORGANIZERS (polymorphic menu)
    public abstract void showMenu();

    //----------------------------VALIDATIONS-------------------------------
    //VALIDATE ID
    public boolean isValidId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return id.matches("[a-zA-Z0-9]+");
    }

    //VALIDATE EMAIL
    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches("[a-zA-Z0-9]+@university.edu");
    }
}
