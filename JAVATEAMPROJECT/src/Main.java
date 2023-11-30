import views.*;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        DefaultFrame win = new DefaultFrame();
        win.setSize(1920,1080);
        //add는 setVisible보다 위에 있어야 됨!
        //win.add(new ProductListCartView(win));
        //win.add(new UserLoginView());
        win.add(new UserLoginView());
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}