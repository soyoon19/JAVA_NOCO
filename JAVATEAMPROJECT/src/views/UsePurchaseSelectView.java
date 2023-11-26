package views;

import custom_component.DefaultFont;
import custom_component.FreeImageIcon;

import javax.swing.*;
import java.awt.*;

public class UsePurchaseSelectView extends JPanel {
    JButton musicUse, productPurchase;
    private static final int IMAGE_X = 400;
    private static final int IMAGE_Y = 400;
    private static final int FONT_SIZE = 50;
    public UsePurchaseSelectView(){
        JPanel gbl = new JPanel(new GridLayout(1,2,5,5));
        this.add(gbl);
        //left
            //클릭 전 버튼
        ImageIcon img1 = new FreeImageIcon(DefaultFrame.PATH+"/images/musicBefore.png",IMAGE_X,IMAGE_Y);
        musicUse = new JButton("곡 사용",img1);
        musicUse.setFont(new DefaultFont(FONT_SIZE));
        musicUse.setContentAreaFilled(false);
            //클릭 후 버튼
        ImageIcon img2 = new FreeImageIcon(DefaultFrame.PATH+"/images/musicAfter.png",IMAGE_X,IMAGE_Y);
        musicUse.setPressedIcon(img2);

        gbl.add(musicUse);

        //right
            //클릭 전 버튼
        ImageIcon img3 = new FreeImageIcon(DefaultFrame.PATH+"/images/coinBefore.png",IMAGE_X,IMAGE_Y);
        productPurchase = new JButton("상품 구매",img3);
        productPurchase.setFont(new DefaultFont(FONT_SIZE));
        productPurchase.setContentAreaFilled(false);
            //클릭 후 버튼
        ImageIcon img4 = new FreeImageIcon(DefaultFrame.PATH+"/images/coinAfter.png",IMAGE_X,IMAGE_Y);
        productPurchase.setPressedIcon(img4);

        gbl.add(productPurchase);
    }
}
