package customexceptions;

/**
 * Created by mario on 27.5.2017..
 */
public class InvalidLoginException extends Exception {

    public InvalidLoginException(String msg) {
        super(msg);
    }
}
