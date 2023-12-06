package views;

import dao.WorkerDAO;
import dto.WorkerDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class WorkerControlView extends JPanel implements ActionListener {

    private JTable table;
    private ArrayList<WorkerDTO> workers;
    WorkerDTO worker;
    private DefaultFrame parent;
    private Vector<Vector<Object>> workerList;

    public WorkerControlView(DefaultFrame prt, WorkerDTO worker) {

        parent = prt;
        String name1=worker.getPosition();
        String name2=worker.getName();


        this.setLayout(new BorderLayout());
        JPanel ct = new JPanel();
        ct.setLayout(new BorderLayout());

        //버튼 배치
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.RIGHT));


        JLabel workName = new JLabel("직책 "+name1+" "+name2+"님");
        JButton reg = new JButton("등록");
        JButton chg = new JButton("수정");
        JButton del = new JButton("삭제");

        reg.addActionListener(this);
        chg.addActionListener(this);
        del.addActionListener(this);

        top.add(workName);
        top.add(reg);
        top.add(chg);
        top.add(del);

        ct.add(top, BorderLayout.NORTH);
        //테이블 배치

        ArrayList<WorkerDTO> workers = prt.getController().getWorkerDAO().findAll();
        Object[] colum = new Object[]{"선택", "순서", "직책", "이름", "전화번호", "ID", "Password", "관리자 권한", "등록일자"};
        Vector<Object>  colVec = new Vector<>();
        for(int i = 0; i < colum.length; i++)
            colVec.add(colum[i]);

        workerList = new Vector<>(new Vector<>());



        DefaultTableModel tableModel = new DefaultTableModel(workerList, colVec){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        // JTable 생성 및 모델 설정
        table = new JTable(tableModel) {
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

        tableUpdate();

        // 첫 번째 열에 RadioCheckBox 추가
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // 스크롤 가능하도록 JScrollPane에 JTable 추가
        JScrollPane scrollPane = new JScrollPane(table);
        ct.add(scrollPane, BorderLayout.CENTER);


        this.add(ct);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(table.getSelectedRow() == -1) {
            //todo 안내
            return;
        }
        WorkerDTO worker = workers.get(table.getSelectedRow());

        switch (s) {
            case "등록":
                (new Worker_regPopup(parent)).setVisible(true);
                break;
            case "수정":
                    (new WorkerCorrectPopup(parent, worker)).setVisible(true);
                break;
            case "삭제":
                    (new WorkerDeleteCheckPopup(parent, worker)).setVisible(true);
                break;
        }
        workerList.removeAllElements();
        tableUpdate();

    }

    public void tableUpdate(){
        int i = 0;

        workers = parent.getController().getWorkerDAO().findAll();
        workerList.removeAllElements();

        for (WorkerDTO worker : workers) {
            Object[] t = new Object[]{false, (i + 1), worker.getPosition(), worker.getName(), worker.getHp(), worker.getId(),
                    worker.getPasswd(), worker.getPosition().equals("점장"), worker.getRegDate()};
            workerList.add(new Vector<>());
            for(int j = 0; j < t.length; j++)
                workerList.get(workerList.size() - 1).add(t[j]);
            i++;
        }

        table.updateUI();
    }


}