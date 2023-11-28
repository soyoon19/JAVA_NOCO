import views.*;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        DefaultFrame win = new DefaultFrame();
        win.setSize(1920,1080);
        //(new Worker_regPopup(win)).setVisible(true);
        //(new WorkerDeleteCheckPopup(win)).setVisible(true);
        //(new WorkerDeleteRCPopup(win)).setVisible(true);
        (new MemberDetailCorrectPopup(win)).setVisible(true);
        //add는 setVisible보다 위에 있어야 됨!
        //win.add(new ProductListCartView(win));
        //win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}