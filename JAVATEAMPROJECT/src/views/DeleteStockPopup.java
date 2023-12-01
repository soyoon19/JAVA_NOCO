package views;

import javax.swing.*;


//음료 삭제 팝업창
class DeleteStockPopup extends JFrame  {
    static int r = JOptionPane.showConfirmDialog(null, "해당 재고를 정말 삭제하겠습니까?",
                  "재고 종류 삭제 확인창", JOptionPane.YES_NO_OPTION);

}