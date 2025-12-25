import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success;
    private String message;
    private Map<String, Object> data;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = new HashMap<>();
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public void setData(String key, Object value) { data.put(key, value); }
    public Object getData(String key) { return data.get(key); }
}