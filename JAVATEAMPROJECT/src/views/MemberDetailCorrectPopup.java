package views;

import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;

public class MemberDetailCorrectPopup extends JDialog {

    public  MemberDetailCorrectPopup(DefaultFrame prt){
        super(prt,"",true);
        this.setSize(500,700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container ct = getContentPane();
        ct.setLayout(new GridBagLayout());

        JPanel top= new JPanel();
        top.setLayout(new BorderLayout());

        //상단 부분
        JPanel topTop= new JPanel();
        JLabel title= new JLabel("회원 정보 수정");

        topTop.add(title);
        top.add(topTop,BorderLayout.NORTH);

        //상단 아래부분


        JLabel hp= new JLabel("전화번호");
        JTextField T_hp= new JTextField(15);

        JLabel passwd= new JLabel("비밀번호");
        JTextField T_passwd= new JTextField(20);

        JLabel r_passwd= new JLabel("비밀번호 확인");
        JTextField T_r_passwd= new JTextField(20);

       JLabel[] jlb= new JLabel[]{hp,passwd,r_passwd};
       JTextField[] jtf= new JTextField[]{T_hp,T_passwd,T_r_passwd};

        JPanel topBtm= new JPanel();
        topBtm.setLayout(new GridLayout(jtf.length,1));

        for(int i=0; i<jtf.length;i++){
            jlb[i].setFont(new DefaultFont(20));
            jtf[i].setFont(new DefaultFont(20));
            GridBagConstraints gbc= new GridBagConstraints();
            gbc.gridx=0;
            gbc.gridy=i;
            gbc.insets= new Insets(5,0,5,5);
            topBtm.add(jlb[i]);

            gbc.gridx=1;
            topBtm.add(jtf[i],gbc);
        }



       top.add(topBtm,BorderLayout.WEST);
        ct.add(top, DefaultFrame.easyGridBagConstraint(0,0,1,5));


        //하단
        JPanel btm= new JPanel();
        btm.setLayout(new BorderLayout());
        JPanel btmTop= new JPanel();
        JLabel rate= new JLabel("등급");
        btmTop.add(rate);
        btm.add(btmTop,BorderLayout.CENTER);

        //등급 라디오 버튼 배치
        JRadioButton[] Jrate = new JRadioButton[3];
        String[] names= new String[]{"Bronze", "Silver", "Gold"};
        ButtonGroup g= new ButtonGroup();
        for(int i=0; i<Jrate.length; i++){
            Jrate[i]= new JRadioButton(names[i]);
            g.add(Jrate[i]);
            btmTop.add(Jrate[i]);
        }
        
        //버튼 배열
        JPanel btmBtm = new JPanel();
        btmBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton chg= new JButton("수정");
        btmBtm.add(chg);
         btm.add(btmBtm,BorderLayout.SOUTH);

      ct.add(btm,DefaultFrame.easyGridBagConstraint(0,1,1,3));
    }
}
