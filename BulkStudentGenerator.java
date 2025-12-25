import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * Generates large numbers of students with properly hashed PINs
 * All students are fully functional and can authenticate
 */
public class BulkStudentGenerator {

    private static final Random rand = new Random();

    // Name arrays for random generation
    private static final String[] FIRST_NAMES = {
            "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda",
            "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica",
            "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa",
            "Matthew", "Betty", "Anthony", "Margaret", "Mark", "Sandra", "Donald", "Ashley",
            "Steven", "Kimberly", "Paul", "Emily", "Andrew", "Donna", "Joshua", "Michelle",
            "Kenneth", "Carol", "Kevin", "Amanda", "Brian", "Dorothy", "George", "Melissa",
            "Edward", "Deborah", "Ronald", "Stephanie", "Timothy", "Rebecca", "Jason", "Sharon",
            "Jeffrey", "Laura", "Ryan", "Cynthia", "Jacob", "Kathleen", "Gary", "Amy",
            "Nicholas", "Shirley", "Eric", "Angela", "Jonathan", "Helen", "Stephen", "Anna",
            "Larry", "Brenda", "Justin", "Pamela", "Scott", "Nicole", "Brandon", "Emma",
            "Benjamin", "Samantha", "Samuel", "Katherine", "Raymond", "Christine", "Gregory", "Debra",
            "Frank", "Rachel", "Alexander", "Catherine", "Patrick", "Carolyn", "Jack", "Janet",
            "Dennis", "Ruth", "Jerry", "Maria", "Tyler", "Heather", "Aaron", "Diane",
            "Jose", "Virginia", "Adam", "Julie", "Henry", "Joyce", "Nathan", "Victoria",
            "Douglas", "Olivia", "Zachary", "Kelly", "Peter", "Christina", "Kyle", "Lauren"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
            "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas",
            "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White",
            "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young",
            "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores",
            "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell",
            "Carter", "Roberts", "Gomez", "Phillips", "Evans", "Turner", "Diaz", "Parker",
            "Cruz", "Edwards", "Collins", "Reyes", "Stewart", "Morris", "Morales", "Murphy",
            "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper", "Peterson", "Bailey",
            "Reed", "Kelly", "Howard", "Ramos", "Kim", "Cox", "Ward", "Richardson",
            "Watson", "Brooks", "Chavez", "Wood", "James", "Bennett", "Gray", "Mendoza",
            "Ruiz", "Hughes", "Price", "Alvarez", "Castillo", "Sanders", "Patel", "Myers",
            "Long", "Ross", "Foster", "Jimenez", "Powell", "Jenkins", "Perry", "Russell"
    };

    public static void main(String[] args) {
        System.out.println("=== Bulk Student Generator ===");
        System.out.println("Creating students with real password hashing...\n");

        // Generate different sets of students
        generateStudentsWithSamePIN(100, "1234");  // 100 students, all use PIN 1234
        generateStudentsWithRandomPINs(50);         // 50 students with random 4-digit PINs

        // Generate and print a list of credentials
        printStudentCredentials(20);

        System.out.println("\n✓ All students created successfully!");
        System.out.println("✓ All PINs are properly hashed and salted");
        System.out.println("✓ Students can now authenticate through the vending machine");
    }

    /**
     * Generate students where all have the same PIN (easy for testing)
     */
    public static void generateStudentsWithSamePIN(int count, String pin) {
        System.out.println("Creating " + count + " students with PIN: " + pin);

        String sql = "INSERT INTO students (student_id, name, pin_hash, salt, balance, weekly_allowance) " +
                "VALUES (?, ?, ?, ?, ?, 50.00)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);
            int successCount = 0;

            for (int i = 1; i <= count; i++) {
                String studentId = String.format("S%05d", i);
                String name = generateRandomName();

                // Generate REAL salt and hash for this student
                String salt = SecurityUtil.generateSalt();
                String pinHash = SecurityUtil.hashPassword(pin, salt);

                // Random balance between $50 and $300
                double balance = 50.0 + (rand.nextDouble() * 250.0);
                BigDecimal balanceDecimal = new BigDecimal(balance).setScale(2, BigDecimal.ROUND_HALF_UP);

                stmt.setString(1, studentId);
                stmt.setString(2, name);
                stmt.setString(3, pinHash);
                stmt.setString(4, salt);
                stmt.setBigDecimal(5, balanceDecimal);
                stmt.addBatch();

                successCount++;

                // Execute batch every 50 students
                if (i % 50 == 0) {
                    stmt.executeBatch();
                    conn.commit();
                    System.out.println("  Created " + i + " students...");
                }
            }

            // Execute remaining batch
            stmt.executeBatch();
            conn.commit();

            System.out.println("✓ Created " + successCount + " students (ID: S00001 to S" +
                    String.format("%05d", count) + ", PIN: " + pin + ")\n");

        } catch (SQLException e) {
            System.err.println("Error creating students: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generate students with random 4-digit PINs
     */
    public static void generateStudentsWithRandomPINs(int count) {
        System.out.println("Creating " + count + " students with random PINs");

        String sql = "INSERT INTO students (student_id, name, pin_hash, salt, balance, weekly_allowance) " +
                "VALUES (?, ?, ?, ?, ?, 50.00)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);
            int startId = 1001; // Start at S01001 to avoid conflicts

            System.out.println("\nStudent Credentials (SAVE THESE!):");
            System.out.println("-----------------------------------");

            for (int i = 0; i < count; i++) {
                String studentId = String.format("S%05d", startId + i);
                String name = generateRandomName();
                String pin = generateRandomPIN();

                // Generate REAL salt and hash
                String salt = SecurityUtil.generateSalt();
                String pinHash = SecurityUtil.hashPassword(pin, salt);

                double balance = 50.0 + (rand.nextDouble() * 250.0);
                BigDecimal balanceDecimal = new BigDecimal(balance).setScale(2, BigDecimal.ROUND_HALF_UP);

                stmt.setString(1, studentId);
                stmt.setString(2, name);
                stmt.setString(3, pinHash);
                stmt.setString(4, salt);
                stmt.setBigDecimal(5, balanceDecimal);
                stmt.addBatch();

                // Print credentials
                System.out.printf("%-10s %-25s PIN: %s%n", studentId, name, pin);

                if ((i + 1) % 50 == 0) {
                    stmt.executeBatch();
                    conn.commit();
                }
            }

            stmt.executeBatch();
            conn.commit();

            System.out.println("-----------------------------------");
            System.out.println("✓ Created " + count + " students with random PINs\n");

        } catch (SQLException e) {
            System.err.println("Error creating students: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generate students and save credentials to a file (optional)
     */
    public static void generateAndSaveCredentials(int count, String filename) {
        System.out.println("Creating " + count + " students and saving to " + filename);

        String sql = "INSERT INTO students (student_id, name, pin_hash, salt, balance, weekly_allowance) " +
                "VALUES (?, ?, ?, ?, ?, 50.00)";

        StringBuilder credentials = new StringBuilder();
        credentials.append("STUDENT CREDENTIALS\n");
        credentials.append("===================\n\n");

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);
            int startId = 2001;

            for (int i = 0; i < count; i++) {
                String studentId = String.format("S%05d", startId + i);
                String name = generateRandomName();
                String pin = generateRandomPIN();

                String salt = SecurityUtil.generateSalt();
                String pinHash = SecurityUtil.hashPassword(pin, salt);

                double balance = 50.0 + (rand.nextDouble() * 250.0);
                BigDecimal balanceDecimal = new BigDecimal(balance).setScale(2, BigDecimal.ROUND_HALF_UP);

                stmt.setString(1, studentId);
                stmt.setString(2, name);
                stmt.setString(3, pinHash);
                stmt.setString(4, salt);
                stmt.setBigDecimal(5, balanceDecimal);
                stmt.addBatch();

                // Save to string
                credentials.append(String.format("%-10s %-25s PIN: %s Balance: $%.2f%n",
                        studentId, name, pin, balance));

                if ((i + 1) % 50 == 0) {
                    stmt.executeBatch();
                    conn.commit();
                }
            }

            stmt.executeBatch();
            conn.commit();

            // Write to file
            java.nio.file.Files.write(
                    java.nio.file.Paths.get(filename),
                    credentials.toString().getBytes()
            );

            System.out.println("✓ Created " + count + " students");
            System.out.println("✓ Credentials saved to " + filename + "\n");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Print a sample of student credentials for testing
     */
    public static void printStudentCredentials(int count) {
        System.out.println("\n=== SAMPLE TEST CREDENTIALS ===");
        System.out.println("Use any of these to test the vending machine:\n");

        String sql = "SELECT student_id, name, balance FROM students ORDER BY student_id LIMIT ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, count);
            var rs = stmt.executeQuery();

            System.out.println("Student ID | Name                      | Balance");
            System.out.println("-----------|---------------------------|----------");

            while (rs.next()) {
                System.out.printf("%-10s | %-25s | $%.2f%n",
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("balance")
                );
            }

            System.out.println("\nAll students S00001-S00100 have PIN: 1234");
            System.out.println("Students S01001+ have random PINs (check output above)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper methods

    private static String generateRandomName() {
        String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

    private static String generateRandomPIN() {
        // Generate random 4-digit PIN (1000-9999)
        return String.format("%04d", 1000 + rand.nextInt(9000));
    }

    /**
     * Delete all students (use carefully!)
     */
    public static void clearAllStudents() {
        System.out.println("WARNING: Deleting all students from database...");

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM students")) {

            int deleted = stmt.executeUpdate();
            System.out.println("✓ Deleted " + deleted + " students\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Example: Custom batch generation
     */
    public static void generateCustomBatch() {
        // Example: Create 25 CS students, 25 Engineering students, etc.
        System.out.println("\n=== Creating Department-Specific Students ===");

        // CS Department - IDs starting with CS
        generateDepartmentStudents("CS", 25, "1111");

        // Engineering - IDs starting with EN
        generateDepartmentStudents("EN", 25, "2222");

        // Business - IDs starting with BU
        generateDepartmentStudents("BU", 25, "3333");

        // Arts - IDs starting with AR
        generateDepartmentStudents("AR", 25, "4444");
    }

    private static void generateDepartmentStudents(String deptCode, int count, String pin) {
        String sql = "INSERT INTO students (student_id, name, pin_hash, salt, balance, weekly_allowance) " +
                "VALUES (?, ?, ?, ?, ?, 50.00)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (int i = 1; i <= count; i++) {
                String studentId = deptCode + String.format("%04d", i);
                String name = generateRandomName();

                String salt = SecurityUtil.generateSalt();
                String pinHash = SecurityUtil.hashPassword(pin, salt);

                double balance = 50.0 + (rand.nextDouble() * 250.0);
                BigDecimal balanceDecimal = new BigDecimal(balance).setScale(2, BigDecimal.ROUND_HALF_UP);

                stmt.setString(1, studentId);
                stmt.setString(2, name);
                stmt.setString(3, pinHash);
                stmt.setString(4, salt);
                stmt.setBigDecimal(5, balanceDecimal);
                stmt.addBatch();
            }

            stmt.executeBatch();
            conn.commit();

            System.out.printf("✓ Created %d %s students (PIN: %s)%n", count, deptCode, pin);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}