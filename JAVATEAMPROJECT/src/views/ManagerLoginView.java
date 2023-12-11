package views;
import controller_db.Controller;
import custom_component.DefaultFont;
import dto.WorkerDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class ManagerLoginView extends JPanel {
    private static final int FONT_SIZE = 80;
    JLabel idlb, pwlb;
    JTextField idtf;
    JPasswordField pwtf;
    JButton loginbt;
    DefaultFrame parent;
    public ManagerLoginView(DefaultFrame prt){
        this.setLayout(new BorderLayout());
        this.parent = prt;

        /// BorderLayout의 Center
        JPanel blc = new JPanel(new GridLayout(4,1));
            //center-1
        blc.add(new JPanel());
            //center-2
        JPanel center2 = new JPanel();
        center2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        idlb = new JLabel("ID : ");
        idlb.setFont(new DefaultFont(FONT_SIZE));
        center2.add(idlb);
        idtf = new JTextField(10);
        idtf.setFont(new DefaultFont(FONT_SIZE));
        center2.add(idtf);
        blc.add(center2);
            //center-3
        JPanel center3 = new JPanel();
        center3.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        pwlb = new JLabel("PW : ");
        pwlb.setFont(new DefaultFont(FONT_SIZE));
        center3.add(pwlb);
        pwtf = new JPasswordField(10);
        pwtf.setFont(new DefaultFont(FONT_SIZE));
        center3.add(pwtf);
        blc.add(center3);
            //center-4
        blc.add(new JPanel());

        add(blc, BorderLayout.CENTER);


        //BorderLayout의 South
        JPanel bls = new JPanel();
        bls.setLayout(new FlowLayout());
        bls.setBackground(Color.gray);

        loginbt = new JButton("LOGIN");
        loginbt.setPreferredSize(new Dimension(500, 100));
        loginbt.setFont(new DefaultFont(FONT_SIZE));
        loginbt.setBackground(Color.white);
        bls.add(loginbt);

        pwtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginbt.doClick();
                }
            }
        });

        loginbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Text Filed에 입력된 값이 없을 경우 예외처리
                if(idtf.getText().equals("")||pwtf.getText().equals("")){
                    JOptionPane.showMessageDialog(parent,"값을 입력해주세요.","입력 오류",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                //Text Filed에 입력된 값이 있다면 아이디를 조회
                WorkerDTO worker = parent.getController().getWorkerDAO().findById(idtf.getText());
                //입력받은 아이디가 DB에 없을 경우 예외처리
                if(worker == null){
                    JOptionPane.showMessageDialog(parent,"일치하는 아이디가 없습니다.","아이디 조회 불가",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if(!worker.getPasswd().equals(pwtf.getText())) { //입력받은 아이디가 DB에 있으나 비밀번호가 일치하지 않을 경우 예외처리
                    JOptionPane.showMessageDialog(parent,"비밀번호가 일치하지 않습니다.","비밀번호 오류",
                            JOptionPane.INFORMATION_MESSAGE);
                } else { //로그인 성공 알림 및 ManageMainView 이동
                    JOptionPane.showMessageDialog(parent,worker.getName()+"님 환영합니다.","관리자 로그인 성공",
                            JOptionPane.INFORMATION_MESSAGE);
                    parent.switchTop(DefaultFrame.TOP_ADMIN);
                    parent.resetMove(new ManagerMainView(parent, worker));
                }
            }
        });

        add(bls, BorderLayout.SOUTH);
    }
}
