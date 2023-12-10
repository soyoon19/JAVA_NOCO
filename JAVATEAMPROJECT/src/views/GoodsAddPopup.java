package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dao.GoodsDAO;
import dto.GoodsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;

public class GoodsAddPopup extends JDialog implements ActionListener {
    private DefaultFrame parent;
    private JLabel imagePathLb;
    private  ButtonGroup stateBtnGrp, eventBtnGrp;
    private JRadioButton eventTRb, eventFRb;
    private String[] states = new String[]{"판매", "품절", "숨김"};
    private JCheckBox iceCB, hotCB;
    private  JTextField codeTf, nameTf, kindTf, amountTf, priceTf, costTf;
    private JButton[] btns;
    private JRadioButton[] stateBrbs;

    public GoodsAddPopup(DefaultFrame prt, GoodsDTO goods){
        this(prt);

        //편집 버튼으로 변경
        btns[2].setText("편집");

        codeTf.setText(goods.getCode());
        codeTf.setEditable(false);
        codeTf.setBackground(Color.white);

        nameTf.setText(goods.getName());
        kindTf.setText(goods.getCategory());
        amountTf.setText(String.valueOf(goods.getSaleCount()));
        priceTf.setText(String.valueOf(goods.getPrice()));
        costTf.setText(String.valueOf(goods.getCost()));
        iceCB.setSelected(goods.getIce());
        hotCB.setSelected(goods.getHot());

        //상태
        int i = 0;
        for(String state : states) {
            if(goods.getStatus().equals(state)) {
                stateBrbs[i].setSelected(true);
                break;
            }
            i++;
        }

        if(goods.getDisStatus()) eventTRb.setSelected(true);
        else eventFRb.setSelected(true);
        imagePathLb.setText(DefaultFrame.PATH + "/images/goods/" + goods.getCode() + ".png");
    }


    public GoodsAddPopup(DefaultFrame prt){
        super(prt, "음료 등록", true);
        this.parent = prt;
        this.setSize(1200, 900);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


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


        for(int i=0;i<tfs.length;i++){
            tfs[i].setFont(new DefaultFont(40));
        }

        //right1-3
        right.add(codeTf); right.add(nameTf); right.add(kindTf);

        //right-4
        stateBrbs = new JRadioButton[states.length];
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
        String[] btnName = new String[]{"취소", "이미지", "저장"};
        btns = new JButton[btnName.length];

        for(int i = 0; i < btnName.length; i++){
            btns[i] =  new JButton(btnName[i]);
            btns[i].addActionListener(this);
            btm.add(btns[i]);
        }

        this.add(btm, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s){
            case "취소":
                break;
            case "편집":
            case "저장":
                GoodsDTO goods = new GoodsDTO();
                goods.setCode(codeTf.getText());
                goods.setName(nameTf.getText());
                goods.setCategory(kindTf.getText());
                goods.setMainCategory(GoodsDTO.MAIN_CATEGORY_DRINK);
                goods.setStatus(states[getSelectedIndex(stateBtnGrp)]);
                goods.setSaleCount(Integer.parseInt(amountTf.getText()));
                goods.setPrice(Integer.parseInt(priceTf.getText()));
                goods.setCost(Integer.parseInt(costTf.getText()));
                goods.setDisStatus(getSelectedIndex(eventBtnGrp) == 0);
                goods.setIce(iceCB.isSelected());
                goods.setHot(iceCB.isSelected());

                GoodsDAO goodsDAO = parent.getController().getGoodsDAO();

                if(s.equals("저장")) {
                    goodsDAO.insert(goods);
                    fileCopy();
                    JOptionPane.showMessageDialog(parent, "추가 완료");
                }else{
                    goodsDAO.update(goods);
                    JOptionPane.showMessageDialog(parent, "편집 완료");
                }

                //DB에 해당 정보 INSERT
                dispose();
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


    public  void fileCopy() {
        Path sourcePath = Paths.get(imagePathLb.getText()); // 복사할 파일의 경로
        Path targetPath = Paths.get(DefaultFrame.PATH + "/images/goods/" + sourcePath.getFileName()); // 복사된 파일이 저장될 경로

        try {
            // 복사
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("파일이 복상 성공.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int getSelectedIndex(ButtonGroup buttonGroup) {
        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        int index = 0;
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return index;
            }
            index++;
        }
        return -1; // No button is selected
    }
}
