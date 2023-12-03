package views;

import dao.WorkerDAO;
import dto.WorkerDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class WorkerControlView extends JPanel {

    public WorkerControlView(DefaultFrame prt) {
        this.setLayout(new BorderLayout());
        JPanel ct= new JPanel();
        ct.setLayout(new BorderLayout());

        //버튼 배치
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel workName= new JLabel("직책 OOO");
        JButton reg= new JButton("등록");
        JButton chg= new JButton("수정");
        JButton del= new JButton("삭제");

        top.add(workName);
        top.add(reg);
        top.add(chg);
        top.add(del);

        ct.add(top, BorderLayout.NORTH);
        //테이블 배치

        ArrayList<WorkerDTO> workers = prt.getController().getWorkerDAO().findAll();
        Object[] colum = new Object[]{"선택", "순서", "직책", "이름", "전화번호", "ID", "Password", "관리자 권한", "등록일자"};
        Object[][] workerData = new Object[workers.size()][];

        int i = 0;
        for(WorkerDTO worker : workers){
            workerData[i] = new Object[]{false, (i + 1), worker.getPosition(), worker.getName(), worker.getHp(), worker.getId(),
                worker.getPasswd(), worker.getPosition().equals("점장"), worker.getRegDate()};
            i++;
        }

        DefaultTableModel tableModel = new DefaultTableModel(workerData, colum);

        // JTable 생성 및 모델 설정
        JTable table = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return Boolean.class; // 첫 번째 열은 Boolean 타입 (RadioCheckBox)
                } else {
                    return super.getColumnClass(column);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // 첫 번째 열만 수정 가능하도록 설정
            }
        };

        // 첫 번째 열에 RadioCheckBox 추가
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // 스크롤 가능하도록 JScrollPane에 JTable 추가
        JScrollPane scrollPane = new JScrollPane(table);
        ct.add(scrollPane, BorderLayout.CENTER);

        this.add(ct);
    }
}
