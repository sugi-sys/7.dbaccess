package ex_member_management;

public class Hobby {
    private Integer id;
    private String name;
    private Integer studentId;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    @Override
    public String toString() {
        return "Hobby [id=" + id + ", name=" + name + ", studentId=" + studentId + "]";
    }

    
}
