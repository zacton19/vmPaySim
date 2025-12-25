import java.util.List;
import java.util.Scanner;

public class VendingMachineClient {
    private String machineId;
    private PaymentClient client;
    private Scanner scanner;
    private StudentDTO currentStudent;

    // Map of machine IDs to locations for display
    private static final String[][] MACHINE_LOCATIONS = {
            {"VM-001", "Student Center - Main Lobby"},
            {"VM-002", "Library - 1st Floor"},
            {"VM-003", "Library - 3rd Floor"},
            {"VM-004", "Engineering Building - Lobby"},
            {"VM-005", "Engineering Building - 2nd Floor"},
            {"VM-006", "Dormitory A - Common Room"},
            {"VM-007", "Dormitory B - Lobby"},
            {"VM-008", "Gym - Main Entrance"},
            {"VM-009", "Science Building - 1st Floor"},
            {"VM-010", "Science Building - 3rd Floor"},
            {"VM-011", "Arts Building - Lobby"},
            {"VM-012", "Cafeteria - East Wing"},
            {"VM-013", "Business School - Atrium"},
            {"VM-014", "Medical Center - Waiting Area"},
            {"VM-015", "Student Union - Food Court"}
    };

    public VendingMachineClient(String serverHost, int serverPort) {
        this.client = new PaymentClient(serverHost, serverPort);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        if (!client.connect()) {
            System.out.println("Cannot connect to payment server. System offline.");
            return;
        }

        // Login once at the start
        if (!authenticate()) {
            client.disconnect();
            return;
        }

        // Main loop - select machines and make purchases
        boolean running = true;
        while (running) {
            selectMachine();
            boolean stayAtMachine = true;

            while (stayAtMachine && running) {
                if (makePurchase()) {
                    // Purchase successful, show menu
                    System.out.println("\nWhat would you like to do?");
                    System.out.println("1. Make another purchase at this machine");
                    System.out.println("2. Go to a different vending machine");
                    System.out.println("3. Exit");
                    System.out.print("Choice: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (choice == 2) {
                        stayAtMachine = false; // Switch machines
                    } else if (choice == 3) {
                        running = false; // Exit program
                        stayAtMachine = false;
                    }
                    // choice == 1 stays in loop for another purchase
                } else {
                    // Purchase failed or cancelled
                    System.out.println("\nWould you like to:");
                    System.out.println("1. Try again at this machine");
                    System.out.println("2. Go to a different vending machine");
                    System.out.println("3. Exit");
                    System.out.print("Choice: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 2) {
                        stayAtMachine = false;
                    } else if (choice == 3) {
                        running = false;
                        stayAtMachine = false;
                    }
                }
            }
        }

        System.out.println("\nThank you for using Campus Vending Machines!");
        client.disconnect();
    }

    private boolean authenticate() {
        System.out.println("=== Campus Vending Machine Network ===");
        System.out.println();

        System.out.print("Swipe your ID card (Enter Student ID): ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();

        currentStudent = client.authenticate(studentId, pin);

        if (currentStudent == null) {
            System.out.println("Authentication failed. Invalid credentials.");
            return false;
        }

        System.out.println("\n✓ Welcome, " + currentStudent.getName() + "!");
        System.out.println("Current Balance: $" + currentStudent.getBalance());
        System.out.println();

        return true;
    }

    private void selectMachine() {
        System.out.println("\n=== Select a Vending Machine ===");

        for (int i = 0; i < MACHINE_LOCATIONS.length; i++) {
            System.out.printf("%2d. %-8s - %s%n",
                    i + 1,
                    MACHINE_LOCATIONS[i][0],
                    MACHINE_LOCATIONS[i][1]
            );
        }

        System.out.print("\nEnter machine number (1-" + MACHINE_LOCATIONS.length + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice < 1 || choice > MACHINE_LOCATIONS.length) {
            System.out.println("Invalid selection. Defaulting to VM-001.");
            machineId = "VM-001";
        } else {
            machineId = MACHINE_LOCATIONS[choice - 1][0];
        }

        String location = getLocationName(machineId);
        System.out.println("\n=== Now at: " + location + " ===");
        System.out.println("Machine ID: " + machineId);
        System.out.println();
    }

    private boolean makePurchase() {
        List<ProductDTO> products = client.getProducts(machineId);

        if (products.isEmpty()) {
            System.out.println("No products available at this machine.");
            return false;
        }

        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            ProductDTO p = products.get(i);
            System.out.printf("%d. %-30s $%.2f%n", i + 1, p.getName(), p.getPrice());
        }

        System.out.print("\nSelect product number (0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 0) {
            System.out.println("Purchase cancelled.");
            return false;
        }

        if (choice < 1 || choice > products.size()) {
            System.out.println("Invalid selection.");
            return false;
        }

        ProductDTO selectedProduct = products.get(choice - 1);

        System.out.println("\nProcessing purchase...");
        Response result = client.processPurchase(
                currentStudent.getStudentId(),
                machineId,
                selectedProduct.getProductId()
        );

        if (result.isSuccess()) {
            System.out.println("✓ " + result.getMessage());
            System.out.println("Dispensing: " + result.getData("productName"));
            System.out.printf("Remaining Balance: $%.2f%n", result.getData("newBalance"));

            // Update current student's balance for display
            currentStudent = new StudentDTO(
                    currentStudent.getStudentId(),
                    currentStudent.getName(),
                    (java.math.BigDecimal) result.getData("newBalance")
            );

            return true;
        } else {
            System.out.println("✗ " + result.getMessage());
            return false;
        }
    }

    private String getLocationName(String machineId) {
        for (String[] machine : MACHINE_LOCATIONS) {
            if (machine[0].equals(machineId)) {
                return machine[1];
            }
        }
        return "Unknown Location";
    }

    public static void main(String[] args) {
        VendingMachineClient client = new VendingMachineClient("localhost", 8888);
        client.start();
    }
}