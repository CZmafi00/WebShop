import customexceptions.DatabaseNotFoundException;
import customexceptions.InvalidLoginException;
import models.LoginViewModel;
import services.AccountService;

import java.io.FileNotFoundException;

/**
 * Created by mario on 16.6.2017..
 */
public class Main {

    public static void main(String[] args) {

      try {

          int a = Integer.parseInt("dsada");

      } catch (Exception e) {
          System.out.println(e.getMessage());
          System.out.println(e.getClass().getName());
      }


    }
}
