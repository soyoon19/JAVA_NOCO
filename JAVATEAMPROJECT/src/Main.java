import views.DefaultFrame;
import views.MusicUseView;
import views.ProductListCartView;

import java.awt.*;
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
        win.add(new ProductListCartView());

        /*DB Test
        ArrayList<GoodsDTO> x = controller.getGoodsDTO().all();
        for(int i = 0; i < x.size(); i++)
            System.out.println(x.get(i).getName());
        */
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}