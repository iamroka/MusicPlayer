package arcanine.rokaplay.model.dto.request;

/**
 * Created by Guru karthik on 06-12-2016.
 */

public class BaseRequest {

    private int status;
    private String message;

    public BaseRequest() {
    }

    public BaseRequest(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
