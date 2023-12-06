package views;

import custom_component.DefaultFont;
import dao.WorkerDAO;
import dto.WorkerDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class WorkerCorrectPopup extends JDialog implements ActionListener, ItemListener {
    public static final int WIDTH = 500, HEIGHT = 700;
    public static final int WIDTH1 = 300, HEIGHT1 = 300;
    private DefaultFrame parent;
    private JTextField T_id, T_passwd, T_r_passwd, T_name, T_hp;
    JRadioButton[] post = new JRadioButton[3];
    private WorkerDTO worker;
    String post1;
    public WorkerCorrectPopup(DefaultFrame prt, WorkerDTO worker) {
        super(prt, "", true);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.parent = prt;
        this.worker = worker;


        Container ct = getContentPane();
        ct.setLayout(new GridBagLayout());

        //top
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        //top-top
        JPanel topTop = new JPanel();
        JLabel infoLb = new JLabel("직원 정보 수정");
        topTop.add(infoLb);
        top.add(topTop, BorderLayout.NORTH);

        //top-bottom
        JPanel topBtm = new JPanel();

        JLabel id = new JLabel("아이디");

        T_id = new JTextField(10);
        T_id.setText(worker.getId());
        T_id.setEditable(false);


        JLabel passwd = new JLabel("비밀번호");
        T_passwd = new JTextField(10);

        T_passwd.setText(worker.getPasswd());
        JLabel r_passwd = new JLabel("비밀번호 확인");

        T_r_passwd = new JTextField(10);

        JLabel name = new JLabel("이름");

        T_name = new JTextField(10);
        T_name.setText(worker.getName());
        JLabel hp = new JLabel("전화번호");

        T_hp = new JTextField(10);
        T_hp.setText(worker.getHp());

        JLabel[] lbs = new JLabel[]{
                id, passwd, r_passwd, name, hp
        };

        JTextField[] tfs = new JTextField[]{
                T_id, T_passwd, T_r_passwd, T_name, T_hp
        };

        topBtm.setLayout(new GridLayout(lbs.length, 1));

        for (int i = 0; i < lbs.length; i++) {
            lbs[i].setFont(new DefaultFont(20));
            tfs[i].setFont(new DefaultFont(20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(5, 5, 5, 5);
            topBtm.add(lbs[i]);

            gbc.gridx = 1;
            topBtm.add(tfs[i], gbc);
        }

        top.add(topBtm, BorderLayout.WEST);
        ct.add(top, DefaultFrame.easyGridBagConstraint(0, 0, 1, 5));

        //bottom
        JPanel btm = new JPanel();
        btm.setLayout(new BorderLayout());

        //bottomCenter
        JPanel btmTop = new JPanel();
        btm.add(btmTop, BorderLayout.CENTER);
        JLabel position = new JLabel("직책");
       post1= worker.getPosition();


        btmTop.add(position);

        String[] post_name = {"작원", "매니저", "점장"};
        ButtonGroup g = new ButtonGroup();
        for (int i = 0; i < post.length; i++) {
            post[i] = new JRadioButton(post_name[i]);
            post[i].addItemListener(this);
            g.add(post[i]);
            btmTop.add(post[i]);
        }


        if(post1.equals("점장")){
            post[2].setSelected(true);
        } else if (post1.equals("매니저")) {
            post[1].setSelected(true);
        }else{
            post[0].setSelected(true);
        }

        //bottomBottom
        JPanel btmBtm = new JPanel();
        btmBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton cor = new JButton("수정");
        cor.addActionListener(this);

        btmBtm.add(cor);
        btm.add(btmBtm, BorderLayout.SOUTH);
        ct.add(btm, DefaultFrame.easyGridBagConstraint(0, 1, 1, 3));
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {

        if (T_passwd.getText().equals(T_r_passwd.getText())) {

            worker.setPasswd(T_passwd.getText());
            worker.setName(T_name.getText());
            worker.setHp(T_hp.getText());

            parent.getController().getWorkerDAO().update(worker);
            JOptionPane.showMessageDialog(parent, "수정 성공!", "알림", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        }

    }

    public void itemStateChanged(ItemEvent ie) {
        if (post[0].isSelected()) {
          post1="직원";
        } else if (post[1].isSelected()) {
            post1="매니저";
        } else if (post[2].isSelected()) {
            post1="점장";
        }
    }
}