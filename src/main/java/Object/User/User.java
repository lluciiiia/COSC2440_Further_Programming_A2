package Object.User;

import java.util.HashMap;

abstract public class User {
    protected String username;
    protected String password;

    //default constructor
    public User () {
        this.username = null;
        this.password = null;
    }

    //initializer
    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    //getter function
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //setter function
    public void setPassword(String password) {
        this.password = password;
    }
}
