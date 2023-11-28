import dto.GoodsDTO;
import views.CardInfoPopup;
import views.DefaultFrame;
import views.MusicUseView;
import views.ProductListCartView;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        DefaultFrame win = new DefaultFrame();
        win.setSize(1920,1080);
        //add는 setVisible보다 위에 있어야 됨!
        //win.add(new ProductListCartView(win));

        //win.add(new MusicUseView(win));
        //win.add(new ProductListCartView(win));
        win.add(new ProductListCartView(win));



        //(new CardInfoPopup(win)).setVisible(true);

        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}