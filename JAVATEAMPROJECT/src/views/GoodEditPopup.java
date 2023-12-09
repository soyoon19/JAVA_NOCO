package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dto.GoodsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GoodEditPopup extends JDialog implements ActionListener {
    private DefaultFrame parent;
    private JLabel imagePathLb;
    private  ButtonGroup stateBtnGrp, eventBtnGrp;
    private String[] states = new String[]{"판매", "품절", "숨김"};
    private JCheckBox iceCB, hotCB;
    private JTextField codeTf, nameTf, kindTf, amountTf, priceTf, costTf;
    private GoodsDTO goods;
    private JButton[] btns;

    public GoodEditPopup(DefaultFrame prt, GoodsDTO goods){
        super(prt, "음료 등록", true);
        this.parent = prt;
        this.setSize(1200, 900);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.goods = goods;

        //center
        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());

        //left
        JPanel left = new JPanel();
        String[] lbsNames = new String[]{"음료 코드", "음료명", "분류", "상태", "ICE/HOT", "판매 가능 개수", "이벤트 여부", "판매가", "원가", "이미지 경로"};

        left.setLayout(new GridLayout(lbsNames.length, 1));
        JPanelOneLabel[] lbs = new JPanelOneLabel[lbsNames.length];

        for(int i = 0; i < lbs.length; i++){
            lbs[i] = new JPanelOneLabel(lbsNames[i]);
            lbs[i].getLabel().setFont(new DefaultFont(40));
            lbs[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            lbs[i].setBorder(BorderFactory.createEmptyBorder(0,20, 0,0));
            left.add(lbs[i]);
        }

        center.add(left, DefaultFrame.easyGridBagConstraint(0,0,1,1));


        //right
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(lbsNames.length ,1));

        JTextField[] tfs = new JTextField[7];
        for(int i  = 0; i < tfs.length; i++){
            tfs[i] = new JTextField(11);
        }

        codeTf = tfs[0]; nameTf = tfs[1]; kindTf = tfs[2];
        amountTf= tfs[3]; priceTf= tfs[4]; costTf= tfs[5];//, imagePath = tfs[6];

        JRadioButton sellRb, soldRb, hideRb;
        JRadioButton eventTRb, eventFRb;

        for(int i=0;i<tfs.length;i++){
            tfs[i].setFont(new DefaultFont(40));
        }

        //right1-3
        right.add(codeTf); right.add(nameTf); right.add(kindTf);

        //right-4
        JRadioButton[] stateBrbs = new JRadioButton[states.length];
        stateBtnGrp = new ButtonGroup();
        JPanel right4 = new JPanel();

        for(int i = 0; i < states.length; i++){
            stateBrbs[i] = new JRadioButton(states[i]);
            stateBrbs[i].setFont(new DefaultFont(40));
            stateBtnGrp.add(stateBrbs[i]);
            right4.add(stateBrbs[i]);

        }
        right.add(right4);

        //right-5
        iceCB = new JCheckBox("ICE");
        hotCB = new JCheckBox("HOT");
        iceCB.setFont(new DefaultFont(40));
        hotCB.setFont(new DefaultFont(40));
        JPanel right5 = new JPanel();

        right5.add(iceCB); right5.add(hotCB);
        right.add(right5);

        //right-6
        right.add(amountTf);


        //right-7
        JPanel right7 = new JPanel();
        eventTRb = new JRadioButton("O");
        eventFRb = new JRadioButton("X");
        eventTRb.setFont(new DefaultFont(40));
        eventFRb.setFont(new DefaultFont(40));
        eventBtnGrp = new ButtonGroup();
        eventBtnGrp.add(eventTRb); eventBtnGrp.add(eventFRb);
        right7.add(eventTRb); right7.add(eventFRb);
        right.add(right7);

        //right8
        right.add(priceTf);

        //right9
        right.add(costTf);

        //right10
        JPanel right10 = new JPanel();
        imagePathLb = new JLabel("");
        right10.add(imagePathLb);
        right.add(right10);


        center.add(right, DefaultFrame.easyGridBagConstraint(1,0,3,1));
        this.add(center, BorderLayout.CENTER);


        //bottom
        JPanel btm = new JPanel();
        String[] btnName = new String[]{"취소", "저장", "이미지"};
        JButton[] btns = new JButton[btnName.length];

        for(int i = 0; i < btnName.length; i++){
            btns[i] =  new JButton(btnName[i]);
            btns[i].addActionListener(this);
            btm.add(btns[i]);
        }


        codeTf.setEditable(false);
        codeTf.setBackground(Color.white);

        this.add(btm, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s){
            case "취소":
                break;
            case "저장":
                //DB에 해당 정보 INSERT
                GoodsDTO goods = new GoodsDTO();
                goods.setCode(codeTf.getText());
                goods.setName(nameTf.getText());
                goods.setCategory(kindTf.getText());
                goods.setMainCategory(GoodsDTO.MAIN_CATEGORY_DRINK);
                //goods.setStatus(states[getSelectedIndex(stateBtnGrp)]);
                goods.setSaleCount(Integer.parseInt(amountTf.getText()));
                goods.setPrice(Integer.parseInt(priceTf.getText()));
                goods.setCost(Integer.parseInt(costTf.getText()));
                //goods.setDisStatus(getSelectedIndex(eventBtnGrp) == 0);
                goods.setIce(iceCB.isSelected());
                goods.setHot(iceCB.isSelected());

                JOptionPane.showMessageDialog(parent, "편집된 음료 정보를 저장했습니다.");
                break;
            case "이미지":
                JFileChooser chooser = new JFileChooser();
                int rst = chooser.showOpenDialog(parent);
                if(rst == JFileChooser.APPROVE_OPTION){
                    File file = chooser.getSelectedFile();
                    imagePathLb.setText(file.getAbsolutePath());
                }
                break;
        }
    }
}
