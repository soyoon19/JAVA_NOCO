package views;

import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserJoinPerssonInfoPopup extends JDialog implements ActionListener{
    private boolean x = false;
    private JCheckBox check;

    public UserJoinPerssonInfoPopup(JFrame parent, JCheckBox check){
        super(parent, "회원 가입", true);
        this.check = check;
        this.setSize(1200,720);

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setBackground(Color.WHITE);
        info.setLineWrap(true);
        info.setFont(new DefaultFont(20));
        info.setText("[필수] 개인정보 수집 및 이용 동의약관\n" +
                "항목 : 아이디(휴대폰번호), 비밀번호, 생년월일\n" +
                "목적 : 회원 가입 및 이용자 식별, 회원관리\n" +
                "보유 및 이용기간 : 회원탈퇴 시 90일간 보관 후 파기\n" +
                "\n" +
                "연령 및 성별 정보는 회원 가입 이후 본인 확인을 진행한 이용자에 한해 활용됩니다.\n" +
                "동의를 거부할 수 있으나 동의 거부 시 서비스 이용이 제한됩니다.\n" +
                "\n" +
                "[필수] 거래내용의 확인\n" +
                "① 회사는 서비스 내 이용자 정보 조회 화면을 통하여 이용자가 자신의 거래내용(이용자의 '오류정정 요구사실 및 처리결과에 관한 사항'을 포함합니다)을 확인할 수 있도록 하며, 이용자가 거래내용에 대하여 서면 교부를 요청하는 경우에는 요청을 받은 날로부터 2주 이내에 모사전송 등의 방법으로 거래내용에 관한 서면을 교부합니다. 다만, 전자적 장치의 운영 장애, 그 밖의 사유로 거래내용을 제공할 수 없는 때에는 즉시 이용자에게 전자문서 전송(전자우편을 이용한 전송을 포함합니다)의 방법으로 그러한 사유를 알려야 하며, 전자적 장치의 운영장애 등의 사유로 거래내용을 제공할 수 없는 기간은 서면교부 기간에 산입하지 아니합니다.\n" +
                "② 제1항의 대상이 되는 거래 내용 중 대상기간이 5년인 것은 다음 각 호와 같습니다.\n" +
                "1. 거래계좌의 명칭 또는 번호\n" +
                "2. 전자금융거래의 종류 및 금액\n" +
                "3. 전자금융거래상대방을 나타내는 정보\n" +
                "4. 전자금융거래일시\n" +
                "5. 전자적 장치의 종류 및 전자적 장치를 식별할 수 있는 정보\n" +
                "6. 회사가 전자금융거래의 대가로 받은 수수료\n" +
                "7. 이용자의 출금 동의에 관한 사항\n" +
                "8. 해당 전자금융거래와 관련한 전자적 장치의 접속기록\n" +
                "9. 전자금융거래의 신청 및 조건의 변경에 관한 사항\n" +
                "10. 건당 거래금액이 1만원을 초과하는 전자금융거래에 관한 기록\n" +
                "③ 제1항의 대상이 되는 거래내용 중 대상기간이 1년인 것은 다음 각호와 같습니다.\n" +
                "1. 전자지급수단 이용과 관련된 거래승인에 관한 기록\n" +
                "2. 이용자의 오류정정 요구사실 및 처리결과에 관한 사항\n" +
                "3. 기타 금융위원회가 고시로 정한 사항\n" +
                "4. 건당 거래금액이 1만원 이하인 소액 전자금융거래에 관한 기록\n" +
                "④ 이용자가 제1항에서 정한 서면교부를 요청하고자 할 경우 다음의 주소 및 전화번호로 요청할 수 있습니다.");

        JScrollPane pane = new JScrollPane(info,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(pane);

        //bottom
        JButton btn =  new JButton("[확인]");
        btn.setPreferredSize(new Dimension(200, 70));
        this.add(btn, BorderLayout.SOUTH);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        check.setSelected(true);
        dispose();
    }
}
