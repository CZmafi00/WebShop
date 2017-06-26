package customexceptions;

/**
 * Created by mario on 25.5.2017..
 */
public class DatabaseNotFoundException extends Exception {

    public DatabaseNotFoundException (){
        super("Database not found. Check config file.");
    }

}
