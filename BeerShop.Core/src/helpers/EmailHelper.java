package helpers;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by mario on 18.6.2017..
 */
public class EmailHelper {

    public static boolean validateEmail(String email) {

        boolean valid = true;

        InternetAddress emailAddr = null;
        try {

            emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException e) {
            valid = false;
        }

        return valid;
    }

}
