package custom_component;

import views.DefaultFrame;

import javax.swing.*;
import java.awt.*;

/*
이미지 크기를 조절하고 싶을 때 편하게 사용할 수 있게
메서드를 기능을 구현한 클래스이다.

모든 이미지는 images 패키지에 저장한다.
만약 어떤 JLabel label에 images 패키지 안에 있는 x.png란 이미지를 50x50의 크기로 변환해서 가져오기 싶다면
label.setIcon(FreeImageIcon.resizeImageIcon(DefaultFrame.PATH + "/images/x.png", 50, 50)로 하면된다.
 */
public class FreeImageIcon extends ImageIcon {

    public FreeImageIcon(String path, int w, int h){
        ImageIcon ii = new ImageIcon(path);
        this.setImage(ii.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }


    //이미지 크기 변공하고 싶을 때 사용!
    public static ImageIcon resizeImageIcon(String path, int w, int h){
        ImageIcon i = new ImageIcon(path);
        ImageIcon rst = new ImageIcon(i.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        return  rst;
    }
}
