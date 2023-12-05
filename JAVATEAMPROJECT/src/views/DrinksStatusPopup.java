package views;


//음료 상태(판매, 품절, 숨김(사용자에게 안보이게))를 변경해주는 팝업창 (개별 음료, 일관 음료 상태 설정에 사용)
import custom_component.DefaultFont;
import dao.GoodsDAO;
import dto.GoodsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


//음료 상태를 보여주는 팝업창
public class DrinksStatusPopup extends JDialog implements ActionListener {


    public static final int WIDTH = 660, HEIGHT = 355;

    public static final Color basicColor = new Color(10,10,10);
    public static final Color selectedColor = new Color(200, 200, 200);

    DefaultFrame parent;
    JButton pastBtn  = null, saleBtn, soldOutBtn, hideBtn;

    public DrinksStatusPopup(DefaultFrame parent, ArrayList<GoodsDTO> goodsAddr) {
        super(parent, "음료 상태를 보여주는 팝업창", true);
        this.parent = parent;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.println(goodsAddr);


        JLabel msg = new JLabel("변경하고 싶은 음료의 상태를 선택해주세요.");
        //상태
        saleBtn = new JButton("판매");
        soldOutBtn = new JButton("품절");
        hideBtn = new JButton("숨김");

        JButton okBtn = new JButton("확인");
        JButton cancleBtn = new JButton("취소");

        msg.setBounds(30,10,600, 65);
        msg.setFont(new DefaultFont(30));

        saleBtn.setBounds(30, 95, 180, 70);
        soldOutBtn.setBounds(230, 95, 180, 70);
        //b3.setBounds(330, 95, 180, 70);
        hideBtn.setBounds(430, 95, 180, 70);

        okBtn.setBounds(30, 195, 180, 60);
        cancleBtn.setBounds(430, 195, 180, 60);

/*
        //배경색 설정
        saleBtn.setBackground(Color.GREEN);*/

        this.add(msg);
        this.add(saleBtn);
        this.add(soldOutBtn);
        this.add(hideBtn);

        saleBtn.addActionListener(this);
        hideBtn.addActionListener(this);

        this.add(okBtn);
        this.add(cancleBtn);

        this.setLayout(null);
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pastBtn == null){
                    System.out.println("Reeturn?");
                    //JOp
                    return;
                }
                GoodsDAO goodsDAO = parent.getController().getGoodsDAO();

                for(GoodsDTO g : goodsAddr){
                    g.setStatus(pastBtn.getText()); //상태 변경!
                    System.out.println(pastBtn.getText());

                    goodsDAO.insert(g);
                }
            }
        });
    }

    //TODO: UPDATE문 삽입후 DB연동 system.out.println(""); 지우기
    @Override
    public void actionPerformed(ActionEvent e) {
        if(pastBtn != null){
            pastBtn.setBackground(basicColor);
        }
        switch (e.getActionCommand()){
            case "판매":
                pastBtn = saleBtn;
                break;
            case "품절":
                pastBtn = soldOutBtn;
                break;
            case "숨김":
                pastBtn = soldOutBtn;
                break;
            case "취소":
                dispose();
                break;
        }
        pastBtn.setBackground(selectedColor);
    }
}

