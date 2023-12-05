package views;
//완성

import custom_component.DefaultFont;
import dto.WorkerDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class WorkerDeleteCheckPopup extends JDialog implements ActionListener {
    DefaultFrame parent;
    WorkerDTO workers;
    public static final int width = 300, height = 300;

    public WorkerDeleteCheckPopup(DefaultFrame prt, WorkerDTO worker) {
        super(prt, "확인창", true);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        parent = prt;
        workers = parent.getController().getWorkerDAO().findById(worker.getId());

        Container ct = getContentPane();
        ct.setLayout(new BoxLayout(ct, BoxLayout.Y_AXIS));

        JLabel message = new JLabel("OOO님을 삭제 하시겠습니까?");


        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setBorder(new EmptyBorder(80, 0, 0, 0));


        JPanel bLayout = new JPanel();
        bLayout.setLayout(new FlowLayout());

        JButton y = new JButton("YES");
        JButton n = new JButton("NO");
        y.addActionListener(this);
        n.addActionListener(this);
        bLayout.add(y);
        bLayout.add(n);

        ct.add(message);
        ct.add(bLayout);

    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case "YES":
                parent.getController().getWorkerDAO().delete(workers.getId());
                (new WorkerDeleteRCPopup(parent)).setVisible(true);
                break;
            case "NO":
                //WorkerDeleteCheckPopup(this).setVisible(false);
                break;
        }
    }
}
