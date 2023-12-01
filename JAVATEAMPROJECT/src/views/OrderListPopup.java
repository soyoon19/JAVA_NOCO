package views;

import custom_component.DefaultFont;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class OrderListPopup extends JDialog {
    class Order {
        String orderCode; // 주문코드
        String phone; // 휴대폰 번호
        String room; // 방 번호
        String[] items; // 상품명 배열
        int[] counts; // 상품 개수 배열
        int[] prices; // 상품 가격 배열
    public Order(String orderCode, String phone, String room, String[] items, int[] counts, int[] prices) {
        this.orderCode = orderCode;
        this.phone = phone;
        this.room = room;
        this.items = items;
        this.counts = counts;
        this.prices = prices;
    }
}


    public static final int WIDTH = 500, HEIGHT = 700;
    public OrderListPopup(DefaultFrame prt){
        super(prt, "", true);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setTitle("주문 정보");
        setLayout(new BorderLayout());

        Order order = new Order("2023113001", "010-1234-5678", "101호",
                new String[]{"커피", "케이크", "샌드위치"}, new int[]{2, 1, 3}, new int[]{3000, 5000, 4000});

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPanel.add(new JLabel("주문리스트" ));


        add(infoPanel, BorderLayout.NORTH);
        // 주문 정보를 담은 2차원 배열
        Object[][] data = new Object[order.items.length][5];
        for (int i = 0; i < order.items.length; i++) {
            data[i][0] = order.items[i];
            data[i][1] = order.counts[i];
            data[i][2] = order.prices[i];
            // 할인 내역 및 결제 금액 (임의로 설정)
            int discount = 500;
            int paymentAmount = order.prices[i] - discount;
            data[i][3] = "할인 " + discount + "원";
            data[i][4] = paymentAmount;
        }

        // 헤더 정보를 담은 배열
        String[] columnNames = {"상품명", "개수", "가격", "할인 내역", "결제 금액"};

        // JTable 생성 및 모델 설정
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // JScrollPane을 사용하여 테이블에 스크롤 추가
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 총 결제 금액 레이블 추가 (가운데 정렬, 큰 글자 크기)
        int totalPayment = 0;
        for (int price : order.prices) {
            totalPayment += price;
        }
        JLabel totalPaymentLabel = new JLabel("총 결제 금액: " + totalPayment + "원");
        totalPaymentLabel.setHorizontalAlignment(JLabel.CENTER);
        totalPaymentLabel.setFont(new DefaultFont(18));
        add(totalPaymentLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}



