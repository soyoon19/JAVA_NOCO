package views;

import javax.swing.*;
import java.awt.*;

public class UsePurchaseSelectView extends JFrame {
    JButton musicUse, productPurchase;
    public UsePurchaseSelectView(){
        this.setLayout(new GridBagLayout());
        JPanel gbl = new JPanel();
        add(gbl);
        //left
        JPanel left = new JPanel();
        this.add(left,DefaultFrame.easyGridBagConstraint(0,0,1,1));
            //클릭 전 버튼
        String imagePath1 = DefaultFrame.PATH+"/images/musicBefore";
        ImageIcon img1 = new ImageIcon(imagePath1);
        musicUse = new JButton("곡 사용",img1);
            //클릭 후 버튼
        String imagePath2 = DefaultFrame.PATH+"/images/musicAfter";
        ImageIcon img2 = new ImageIcon(imagePath2);
        musicUse.setPressedIcon(img2);
        gbl.add(left);
        //right
        JPanel right = new JPanel();
        this.add(right,DefaultFrame.easyGridBagConstraint(1,0,1,1));
            //클릭 전 버튼
        String imagePath3 = DefaultFrame.PATH+"/images/coinBefore";
        ImageIcon img3 = new ImageIcon(imagePath3);
        productPurchase = new JButton("상품 구매",img3);
            //클릭 후 버튼
        String imagePath4 = DefaultFrame.PATH+"/images/coinAfter";
        ImageIcon img4 = new ImageIcon(imagePath4);
        productPurchase.setPressedIcon(img4);
        gbl.add(right);
    }
}
