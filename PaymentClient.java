import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PaymentClient {
    private String serverHost;
    private int serverPort;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public PaymentClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public boolean connect() {
        try {
            socket = new Socket(serverHost, serverPort);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to payment server");
            return true;
        } catch (IOException e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
            return false;
        }
    }

    public void disconnect() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response sendRequest(Request request) throws IOException, ClassNotFoundException {
        out.writeObject(request);
        out.flush();
        return (Response) in.readObject();
    }

    public StudentDTO authenticate(String studentId, String pin) {
        try {
            Request request = new Request(Request.RequestType.AUTHENTICATE);
            request.setData("studentId", studentId);
            request.setData("pin", pin);

            Response response = sendRequest(request);

            if (response.isSuccess()) {
                return (StudentDTO) response.getData("student");
            }
        } catch (Exception e) {
            System.err.println("Authentication error: " + e.getMessage());
        }
        return null;
    }

    public List<ProductDTO> getProducts(String machineId) {
        try {
            Request request = new Request(Request.RequestType.GET_PRODUCTS);
            request.setData("machineId", machineId);

            Response response = sendRequest(request);

            if (response.isSuccess()) {
                return (List<ProductDTO>) response.getData("products");
            }
        } catch (Exception e) {
            System.err.println("Error getting products: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public Response processPurchase(String studentId, String machineId, String productId) {
        try {
            Request request = new Request(Request.RequestType.PROCESS_PURCHASE);
            request.setData("studentId", studentId);
            request.setData("machineId", machineId);
            request.setData("productId", productId);

            return sendRequest(request);
        } catch (Exception e) {
            System.err.println("Purchase error: " + e.getMessage());
            return new Response(false, "Network error");
        }
    }
}