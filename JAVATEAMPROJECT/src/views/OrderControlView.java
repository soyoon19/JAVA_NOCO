package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class OrderControlView extends JPanel {
    public OrderControlView(DefaultFrame prt) {

        JPanel ct = new JPanel();

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

        JButton back = new JButton("icon");
        JLabel title = new JLabel("주문 내역 관리");
        JLabel post = new JLabel("직책 OOO");

        top1.add(back);
        top2.add(title);
        top3.add(post);

        top.add(top1);
        top.add(top2);
        top.add(top3);
        main.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        // 테이블 모델
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{
                        {false, 1, "2023-01-01 12:00:00", "101호", "커피, 케이크", 12000, "2023-01-01 13:30:00", "완료"},
                        {false, 2, "2023-01-02 14:30:00", "102호", "음료수, 샌드위치", 15000, "2023-01-02 15:45:00", "완료"},

                },
                new Object[]{"", "순서", "접수시간", "방번호", "주문내역", "결제액", "완료시간", "완료버튼"}
        );

        // 첫 번째 열에 체크박스
        JTable table = new JTable(tableModel) {
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
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        center.add(scrollPane, BorderLayout.CENTER);

        main.add(center,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton detail = new JButton("상세 내역 확인");
        JButton cancel = new JButton("결제취소");

        bottom.add(detail);
        bottom.add(cancel);
        main.add(bottom, BorderLayout.SOUTH);
        ct.add(main);

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
}
