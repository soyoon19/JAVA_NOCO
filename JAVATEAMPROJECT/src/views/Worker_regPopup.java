package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Worker_regPopup extends JFrame {

    Worker_regPopup() {
        Container ct = getContentPane();
        ct.setLayout(new GridBagLayout());

        JRadioButton[] post = new JRadioButton[3];
        String[] post_name = {"작원", "매니저", "점장"};


        JPanel top = new JPanel();
        JPanel down = new JPanel();
        JPanel top_layout = new JPanel();
        JPanel down_layout1 = new JPanel();
        JPanel down_layout2 = new JPanel();
        JPanel radioPanel = new JPanel();

        ButtonGroup g = new ButtonGroup();
        for (int i = 0; i < post.length; i++) {
            post[i] = new JRadioButton(post_name[i]);
            g.add(post[i]);
            radioPanel.add(post[i]);
        }

        top.setLayout(new BorderLayout());
        down.setLayout(new BorderLayout());
        top_layout.setLayout(new FlowLayout());
        down_layout1.setLayout(new FlowLayout(FlowLayout.LEFT));
        down_layout2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel title = new JLabel("직원 정보 등록");
        JTextArea T_title = new JTextArea("'");

        JLabel id = new JLabel("아이디");
        JTextArea T_id = new JTextArea("");

        JLabel passwd = new JLabel("비밀번호");
        JTextArea T_passwd = new JTextArea("");

        JLabel r_passwd = new JLabel("비밀번호 확인");
        JTextArea T_r_passwd = new JTextArea("");

        JLabel name = new JLabel("이름");
        JTextArea T_name = new JTextArea("");

        JLabel hp = new JLabel("전화번호");
        JTextArea T_hp = new JTextArea("");

        JLabel position = new JLabel("직책");

        JButton reg = new JButton("등록");


        ct.add(top);
        ct.add(down);

        top.add(title, BorderLayout.NORTH);
        top.add(top_layout, BorderLayout.CENTER);
        down.add(down_layout1, BorderLayout.CENTER);
        down.add(down_layout2, BorderLayout.SOUTH);

        top_layout.add(title);
        top_layout.add(T_title);
        top_layout.add(id);
        top_layout.add(T_id);
        top_layout.add(passwd);
        top_layout.add(T_passwd);
        top_layout.add(r_passwd);
        top_layout.add(T_r_passwd);
        top_layout.add(name);
        top_layout.add(T_name);
        top_layout.add(hp);
        top_layout.add(T_hp);

        post[0].setSelected(true);
        down_layout1.add(position);
        down_layout1.add(radioPanel);


        down_layout2.add(reg);
    }
}