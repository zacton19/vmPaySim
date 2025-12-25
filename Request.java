import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum RequestType {
        AUTHENTICATE,
        GET_BALANCE,
        GET_PRODUCTS,
        PROCESS_PURCHASE,
        GET_TRANSACTION_HISTORY,
        HEARTBEAT
    }

    private RequestType type;
    private Map<String, Object> data;

    public Request(RequestType type) {
        this.type = type;
        this.data = new HashMap<>();
    }

    public RequestType getType() { return type; }
    public void setData(String key, Object value) { data.put(key, value); }
    public Object getData(String key) { return data.get(key); }
    public Map<String, Object> getAllData() { return data; }
}