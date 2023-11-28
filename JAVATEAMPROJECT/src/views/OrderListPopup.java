package views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
public class OrderListPopup extends JDialog {
    public static final int WIDTH = 500, HEIGHT = 700;
    public OrderListPopup(DefaultFrame prt){
        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] list= new String[]{"아메리카노","달고나라떼","홍시스무디"};//임시 데이터 상품 명
        int[] su= new int[]{1,3,1};//임시 데이터 상품 개수
        int[] cost=new int[]{3000,4000,7000};


        JPanel mLayout= new JPanel(); // 메인 패널
        mLayout.setLayout(new BorderLayout());

        //타이틀 배치
        JLabel title = new JLabel("주문 내역");// 타이틀 명
        mLayout.add(title,BorderLayout.NORTH);

        // 주문 내역 layout


        JPanel goods= new JPanel();
        JPanel g_list= new JPanel();

        goods.setLayout(new BorderLayout());


        //상품내역 top 부분
        JLabel g_name=new JLabel("상품명");
        JLabel g_count= new JLabel("개수");
        JLabel g_pay=new JLabel("가격");

        JLabel[] g_head= new JLabel[]{
                g_name,g_count,g_pay
        };

        for(int i=0; i<g_head.length; i++){
            JPanel o= new JPanel();
            o.add(g_head[i]);
            goods.add(o,BorderLayout.NORTH);
        }

        //상품내역 middle
        Container con= getContentPane();
        g_list.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));//세로 정렬

    }

}
