package views;

import custom_component.DefaultFont;
import dto.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class OrderControlView extends JPanel implements ActionListener {
    private JTable jTable;
    private ArrayList<MemberDTO>members;
    private WorkerDTO worker;
    private ArrayList<RoomManageDTO> rooms;
    private ArrayList<OrderDTO> orders;
    private ArrayList<OrderHDTO> orderH;
    private ArrayList<GoodsDTO> goods;
    DefaultFrame parent;
    public OrderControlView(DefaultFrame prt, WorkerDTO worker) {

        this.parent=prt;
        members=parent.getController().getMemberDAO().findAll();

        this.worker = worker;
        rooms=parent.getController().getRoomManageDAO().findAll();
        orders=parent.getController().getOrderDAO().findAll();
        orderH=parent.getController().getOrderHDAO().findAll();
        goods=parent.getController().getGoodsDAO().findAll();

        JPanel ct = new JPanel();
        ct.setLayout(new BorderLayout());

        ct.setPreferredSize(new Dimension(1400,800));
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 3));

        JPanel top1 = new JPanel();
        top1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel top2 = new JPanel();
        top2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel top3 = new JPanel();
        top3.setLayout(new FlowLayout(FlowLayout.RIGHT));


        JLabel title = new JLabel("주문 내역 관리");
        title.setFont(new DefaultFont(30));
        String name1=  worker.getPosition();
        String name2= worker.getName();
        
        JLabel post = new JLabel("직책 "+name1+" "+name2+"님");
        
      


        top2.add(title);
        top3.add(post);

        top.add(top1);
        top.add(top2);
        top.add(top3);
        main.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        // 테이블 모델

        Object[] colum= new Object[]{"선택", "순서","주문코드" ,"접수시간", "방번호", "주문내역", "결제액", "완료시간", "완료버튼"};
        Vector<Object> colVec = new Vector<>();
        for(int i = 0; i < colum.length; i++)
            colVec.add(colum[i]);

        int i = 1;
        Vector<Vector<Object>> listDate = new Vector<>(new Vector<>());
        for(OrderDTO order : orders){
            listDate.add(new Vector<>());
            listDate.get(listDate.size() - 1).add(false);
            listDate.get(listDate.size() - 1).add(i);
            listDate.get(listDate.size() - 1).add(order.getCode());
            listDate.get(listDate.size() - 1).add(order.getTime().toString());
            listDate.get(listDate.size() - 1).add(parent.getController().getRoomManageDAO().findById(order.getCode()).getNum());
            listDate.get(listDate.size() - 1).add(0);
            listDate.get(listDate.size() - 1).add(order.getPay());
            listDate.get(listDate.size() - 1).add(order.getComptime());
            listDate.get(listDate.size() - 1).add("완료");
            i++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(listDate, colVec){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        // 첫 번째 열에 체크박스
     jTable = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(column);
                }
            }
        };

        // 완료버튼에 대한 렌더러 및 에디터 추가
        jTable.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        jTable.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(jTable);
        center.add(scrollPane, BorderLayout.CENTER);

        main.add(center,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton detail = new JButton("상세 내역 확인");
        detail.addActionListener(this);
        JButton cancel = new JButton("결제취소");
        cancel.addActionListener(this);

        bottom.add(detail);
        bottom.add(cancel);
        main.add(bottom, BorderLayout.SOUTH);
        ct.add(main);
        this.add(ct);
    }

    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // 완료버튼 에디터 클래스
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            button.setText((value == null) ? "" : value.toString());
            return button;
        }


        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }

    public void actionPerformed(ActionEvent e){
        String s= e.getActionCommand();
        OrderDTO order = orders.get(jTable.getSelectedRow());
        switch (s){
            case "상세 내역 확인":
                        (new OrderDetailPopup(parent)).setVisible(true);
                break;
            case"결제취소":
                (new OrderDeletePopup(parent)).setVisible(true);
                break;
        }
    }
}
