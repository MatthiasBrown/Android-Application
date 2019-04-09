package e.irvingarcia.project;

public class Admin {
    String username;
    String password;
    String firstName;
    public Admin(String user,String pass,String first){
        username=user;
        password=pass;
        firstName=first;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }
}
