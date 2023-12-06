package views;


import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//재고 추가, 편집
// (관리자 로그인 시, 재고관리 페이지에서 확인 가능)
public class StockMgPopup extends JDialog implements ActionListener {

    public static final int WIDTH = 800, HEIGHT = 100;

    DefaultFrame parent;

    public StockMgPopup(DefaultFrame parent) {
        super(parent, "재고 상세 관리 팝업", true);
        this.parent = parent;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        //left -1 (JLable)
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());


        //left -1 (JTable)
        JTable table;

        String[] columnType = {"음료코드", "음료명", "분류", "상태", "판매 가능 개수", "이벤트 여부", "판매가", "단가"};

        //columnType.setFont(new DefaultFont(50));

        Object[][] drinksData = {
                //테스트를 위한 데이터
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
        };


        table = new JTable(drinksData, columnType);
        left.add(table, BorderLayout.CENTER);


        //Table에 scrollPane 붙이기
        //table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane 올리기
        main.add(scrollPane);


        //!!! 팀장님이랑 생각해보기!
        //right - 1 (해당 음료 이미지, 이미지 변경 버튼, 취소, 저장 버튼)
        JPanel right = new JPanel();
        //right.setLayout(new );

        //right - 3 : 저장 버튼
        JButton okBtn = new JButton("저장");
        okBtn.setFont(new DefaultFont(20));
        right.add(okBtn);

        //right - 3 : 취소 버튼
        JButton canBtn = new JButton("취소");
        canBtn.setFont(new DefaultFont(20));
        right.add(canBtn);


        main.add(right, BorderLayout.EAST);

        //호출시 보이기 설정
        this.add(main);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}