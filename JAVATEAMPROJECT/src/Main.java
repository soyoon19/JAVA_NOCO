import views.*;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        DefaultFrame win = new DefaultFrame();
        win.setSize(1920,1080);

        //(new MemberControlView(win)).setVisible(true);//!
        //(new MemberDeletePopup(win)).setVisible(true);
        //(new MemberDetailCorrectPopup(win)).setVisible(true);
        //(new MemberDetailPopup(win)).setVisible(true);

        //(new NoMemberControlView(win)).setVisible(true); //!

        //(new OrderControlView(win)).setVisible(true); //!
        //(new OrderDeleteCheckPopup(win)).setVisible(true);
        //(new OrderDeletePopup(win)).setVisible(true);
        //(new OrderDeleteRCPopup(win)).setVisible(true);
        //(new OrderDetailPopup(win)).setVisible(true);
        //(new OrderListPopup(win)).setVisible(true);

        //(new SalesAnalysisDetailPopup(win)).setVisible(true);
        //(new SalesAnalysisView(win)).setVisible(true);
        //(new SalesCheckPopup(win)).setVisible(true);
        //(new SalesDetailPopup(win)).setVisible(true);

        //(new Worker_regPopup(win)).setVisible(true);
        //(new WorkerControlView(win)).setVisible(true);
        //(new WorkerDeleteCheckPopup(win)).setVisible(true);
        //(new WorkerDeleteRCPopup(win)).setVisible(true);
        //(new WorkerCorrectPopup(win)).setVisible(true);


        //add는 setVisible보다 위에 있어야 됨!
        //win.add(new ProductListCartView(win));
        win.add(new MemberControlView(win));
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}