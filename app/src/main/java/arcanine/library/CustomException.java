package arcanine.library;

/**
 * Created by Rohan on 05/12/2016.
 */
public class CustomException extends Exception {

    private int code;

    private String exception;

    public CustomException(int code, String exception) {
        this.code = code;
        this.exception = exception;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
