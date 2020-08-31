package catalog.entities;

public class StudentInfo {
    public int ssn;
    public String firstName;
    public String lastName;

    public StudentInfo(int ssn, String firstName, String lastName) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
