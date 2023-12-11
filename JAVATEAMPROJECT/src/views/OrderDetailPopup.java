package views;

import custom_component.DefaultFont;
import dao.GoodsDAO;
import dao.OrderDAO;
import dao.OrderHDAO;
import dto.OrderDTO;
import dto.OrderHDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class OrderDetailPopup extends JDialog {
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

    private DefaultFrame parent;
    private OrderDTO order;

    public static final int WIDTH = 300, HEIGHT = 700;

    public OrderDetailPopup(DefaultFrame prt, OrderDTO order) {

        super(prt, "", true);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.parent = prt;
        setTitle("주문 정보");
        setLayout(new BorderLayout());

        OrderHDAO orderHDAO = parent.getController().getOrderHDAO();
        GoodsDAO goodsDAO = parent.getController().getGoodsDAO();

        this.order = order;
        System.out.println(order.getCode());
        ArrayList<OrderHDTO> orderH = orderHDAO.findOrderCode(order.getO_code());

        String[] names = new String[orderH.size()];
        int[] nums = new int[orderH.size()];
        int[] prices = new int[orderH.size()];

        for(int i = 0; i < orderH.size(); i++){
            names[i] = goodsDAO.findById(orderH.get(i).getGoodsCode()).getName();
            nums[i] = orderH.get(i).getCount();
            prices[i] = orderH.get(i).getPrice();
        }

        Order order1 = new Order(order.getO_code(), order.getHp(), order.getCode() + "번 방",
                names, nums, prices
                );


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1));
        infoPanel.add(new JLabel("주문코드: " + order1.orderCode));
        infoPanel.add(new JLabel("휴대폰 번호: " + order1.phone));
        infoPanel.add(new JLabel("방 번호: " + order1.room));
        add(infoPanel, BorderLayout.NORTH);
        // 주문 정보를 담은 2차원 배열
        Object[][] data = new Object[order1.items.length][5];
        for (int i = 0; i < order1.items.length; i++) {
            data[i][0] = order1.items[i];
            data[i][1] = order1.counts[i];
            data[i][2] = order1.prices[i];
            // 할인 내역 및 결제 금액 (임의로 설정)
            int discount = 500;
            int paymentAmount = order1.prices[i] - discount;
            data[i][3] = discount;
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
        for (int price : order1.prices) {
            totalPayment += price;
        }

        JLabel totalPaymentLabel = new JLabel("총 결제 금액: " + totalPayment + "원");
        totalPaymentLabel.setHorizontalAlignment(JLabel.CENTER);
        totalPaymentLabel.setFont(new DefaultFont(18));
        add(totalPaymentLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
