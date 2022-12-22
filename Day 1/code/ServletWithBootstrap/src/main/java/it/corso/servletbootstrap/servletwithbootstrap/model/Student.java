package it.corso.servletbootstrap.servletwithbootstrap.model;

public class Student {
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

    private String username;
    private String password;


    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
