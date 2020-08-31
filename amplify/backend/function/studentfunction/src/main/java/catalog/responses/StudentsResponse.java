package catalog.responses;

import catalog.entities.StudentInfo;

public class StudentsResponse {
    StudentInfo[] students;

    public StudentsResponse(StudentInfo[] response) {
        students = response;
    }

    public StudentInfo[] getResponse() {
        return students;
    }

    public void setResponse(StudentInfo[] newResponse) {
        students = newResponse;
    }
}
