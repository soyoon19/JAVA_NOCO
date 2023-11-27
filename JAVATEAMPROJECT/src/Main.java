import views.*;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DefaultFrame win = new DefaultFrame();
        win.setSize(1920,1080);
        win.add(new RoomSettingView(win));
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}