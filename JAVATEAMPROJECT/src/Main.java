import controller_db.Controller;
import controller_db.DBConnect;
import views.*;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

        DBConnect db = new DBConnect();
        Controller controller =  new Controller(db.getConn());

        DefaultFrame win = new DefaultFrame(controller);
        win.setSize(1920,1080);
        //add는 setVisible보다 위에 있어야 됨!

        //win.add(new DrinksManagementView(win));
        // new DrinkMgPopup(win);
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}