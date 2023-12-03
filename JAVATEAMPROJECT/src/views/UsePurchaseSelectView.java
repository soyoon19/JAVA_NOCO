package views;

import custom_component.DefaultFont;
import custom_component.FreeImageIcon;
import dao.MemberDAO;
import dao.MemberLogDAO;
import dto.MemberDTO;
import dto.MemberLogDTO;

import javax.swing.*;
import java.awt.*;

public class UsePurchaseSelectView extends JPanel {
    JButton musicUse, productPurchase, roomExit;
    private static final int IMAGE_X = 500;
    private static final int IMAGE_Y = 500;
    private static final int FONT_SIZE = 80;
    public UsePurchaseSelectView(MemberDTO member){
        setLayout(new BorderLayout());

        //top
        JPanel bot = new JPanel(new GridLayout(1,2,15,15));
        add(bot);

            //left
                //클릭 전 버튼
        ImageIcon img1 = new FreeImageIcon(DefaultFrame.PATH+"/images/musicBefore.png",IMAGE_X,IMAGE_Y);
        musicUse = new JButton("곡 사용",img1);
        musicUse.setBackground(Color.white);
        musicUse.setFont(new DefaultFont(FONT_SIZE));
        musicUse.setContentAreaFilled(false);
                //클릭 후 버튼
        ImageIcon img2 = new FreeImageIcon(DefaultFrame.PATH+"/images/musicAfter.png",IMAGE_X,IMAGE_Y);
        musicUse.setPressedIcon(img2);

        bot.add(musicUse,BorderLayout.CENTER);

            //right
                //클릭 전 버튼
        ImageIcon img3 = new FreeImageIcon(DefaultFrame.PATH+"/images/coinBefore.png",IMAGE_X,IMAGE_Y);
        productPurchase = new JButton("상품 구매",img3);
        productPurchase.setBackground(Color.white);
        productPurchase.setFont(new DefaultFont(FONT_SIZE));
        productPurchase.setContentAreaFilled(false);
                //클릭 후 버튼
        ImageIcon img4 = new FreeImageIcon(DefaultFrame.PATH+"/images/coinAfter.png",IMAGE_X,IMAGE_Y);
        productPurchase.setPressedIcon(img4);

        bot.add(productPurchase);

        //bottom
        if(member != null) {
            roomExit = new JButton("방 퇴장");
            roomExit.setFont(new DefaultFont(FONT_SIZE));
            roomExit.setBackground(Color.white);

            add(roomExit,BorderLayout.SOUTH);
        }
    }
}
