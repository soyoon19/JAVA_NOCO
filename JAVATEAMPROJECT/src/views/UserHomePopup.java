package views;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserHomePopup {
    public static void main(String[] args) {
        // UserHomeView - 비회원으로 이용하기 버튼에서 넘어옴
    }

    private static void showDialog(JFrame parentFrame) {
        // JDialog 생성
        JDialog dialog = new JDialog(parentFrame, "알림", true);
        dialog.setSize(300, 150);
        dialog.setLayout(null);

        // 팝업 메시지 레이블 생성
        JLabel messageLabel = new JLabel("비회원 이용시 할인혜택 및 적립불가. 회원가입 하시겠습니까?");
        messageLabel.setBounds(10, 10, 280, 30);
        dialog.add(messageLabel);

        // 예 버튼 생성 및 이벤트 핸들러 등록
        JButton yesButton = new JButton("예");
        yesButton.setBounds(30, 60, 80, 30);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 회원가입 페이지로 이동하는 코드 작성
                System.out.println("회원가입 페이지로 이동합니다.");
                dialog.dispose(); // 창 닫기
            }
        });
        dialog.add(yesButton);

        // 아니오 버튼 생성 및 이벤트 핸들러 등록
        JButton noButton = new JButton("아니오");
        noButton.setBounds(150, 60, 80, 30);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 방 선택 페이지로 이동하는 코드 작성
                System.out.println("방 선택 페이지로 이동합니다.");
                dialog.dispose(); // 창 닫기
            }
        });
        dialog.add(noButton);

        // X 버튼 클릭 시 이벤트 핸들러 등록
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // 창을 닫을 때의 동작 작성
                // 여기서는 창을 닫기만 함
                dialog.dispose();
            }
        });

        // 다이얼로그 표시
        dialog.setVisible(true);
    }
}
