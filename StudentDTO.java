import java.io.Serializable;
import java.math.BigDecimal;

public class StudentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String name;
    private BigDecimal balance;

    public StudentDTO(String studentId, String name, BigDecimal balance) {
        this.studentId = studentId;
        this.name = name;
        this.balance = balance;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public BigDecimal getBalance() { return balance; }
}