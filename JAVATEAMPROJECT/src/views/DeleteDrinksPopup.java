package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//음료 삭제 팝업창
public class DeleteDrinksPopup extends JFrame  {
    static int r = JOptionPane.showConfirmDialog(null, "해당 음료를 정말 삭제하겠습니까?",
                   "음료 종류 삭제 확인창", JOptionPane.YES_NO_OPTION);

}