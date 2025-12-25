import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.math.BigDecimal;
import java.util.List;

public class PaymentServer {
    private static final int PORT = 8888;
    private StudentDAO studentDAO;
    private ProductDAO productDAO;
    private TransactionService transactionService;

    public PaymentServer() {
        this.studentDAO = new StudentDAO();
        this.productDAO = new ProductDAO();
        this.transactionService = new TransactionService();
    }

    public void start() {
        System.out.println("=== Campus Payment Server Starting ===");
        System.out.println("Listening on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    Request request = (Request) in.readObject();
                    Response response = handleRequest(request);
                    out.writeObject(response);
                    out.flush();
                }

            } catch (EOFException e) {
                System.out.println("Client disconnected");
            } catch (Exception e) {
                System.err.println("Error handling client: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private Response handleRequest(Request request) {
            System.out.println("Processing request: " + request.getType());

            switch (request.getType()) {
                case AUTHENTICATE:
                    return handleAuthentication(request);

                case GET_BALANCE:
                    return handleGetBalance(request);

                case GET_PRODUCTS:
                    return handleGetProducts(request);

                case PROCESS_PURCHASE:
                    return handlePurchase(request);

                case HEARTBEAT:
                    return new Response(true, "Server alive");

                default:
                    return new Response(false, "Unknown request type");
            }
        }

        private Response handleAuthentication(Request request) {
            String studentId = (String) request.getData("studentId");
            String pin = (String) request.getData("pin");

            StudentDTO student = studentDAO.authenticateStudent(studentId, pin);

            if (student != null) {
                studentDAO.addWeeklyAllowance(studentId);
                BigDecimal currentBalance = studentDAO.getBalance(studentId);
                student = new StudentDTO(student.getStudentId(), student.getName(), currentBalance);

                Response response = new Response(true, "Authentication successful");
                response.setData("student", student);
                return response;
            } else {
                return new Response(false, "Invalid credentials");
            }
        }

        private Response handleGetBalance(Request request) {
            String studentId = (String) request.getData("studentId");
            BigDecimal balance = studentDAO.getBalance(studentId);

            Response response = new Response(true, "Balance retrieved");
            response.setData("balance", balance);
            return response;
        }

        private Response handleGetProducts(Request request) {
            String machineId = (String) request.getData("machineId");
            List<ProductDTO> products = productDAO.getAvailableProducts(machineId);

            Response response = new Response(true, "Products retrieved");
            response.setData("products", products);
            return response;
        }

        private Response handlePurchase(Request request) {
            String studentId = (String) request.getData("studentId");
            String machineId = (String) request.getData("machineId");
            String productId = (String) request.getData("productId");

            return transactionService.processPurchase(studentId, machineId, productId);
        }
    }

    public static void main(String[] args) {
        PaymentServer server = new PaymentServer();
        server.start();
    }
}