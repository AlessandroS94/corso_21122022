package it.nttdata.corso.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    private String fullname;
    
    private String email;
    
    private char gender = 'M';
    
    @ManyToOne
    private Department department;

    public Employee() {
    }
    
    public Employee(String fullname, String email, char gender) {
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee {id=" + this.getId() + ", fullname=" + fullname + 
                ", email=" + email + ", gender=" + gender + '}';
    }
}
