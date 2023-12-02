package views;
import controller_db.Controller;
import custom_component.DefaultFont;
import dto.WorkerDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class ManagerLoginView extends JPanel {
    private static final int FONT_SIZE = 80;
    JLabel idlb, pwlb;
    JTextField idtf;
    JPasswordField pwtf;
    JButton loginbt;
    DefaultFrame parent;
    public ManagerLoginView(DefaultFrame prt){
        this.setLayout(new BorderLayout());
        this.parent = prt;

        /// BorderLayout의 Center
        JPanel blc = new JPanel(new GridLayout(4,1));
            //center-1
        blc.add(new JPanel());
            //center-2
        JPanel center2 = new JPanel();
        center2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        idlb = new JLabel("ID : ");
        idlb.setFont(new DefaultFont(FONT_SIZE));
        center2.add(idlb);
        idtf = new JTextField(10);
        idtf.setFont(new DefaultFont(FONT_SIZE));
        center2.add(idtf);
        blc.add(center2);
            //center-3
        JPanel center3 = new JPanel();
        center3.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        pwlb = new JLabel("PW : ");
        pwlb.setFont(new DefaultFont(FONT_SIZE));
        center3.add(pwlb);
        pwtf = new JPasswordField(10);
        pwtf.setFont(new DefaultFont(FONT_SIZE));
        center3.add(pwtf);
        blc.add(center3);
            //center-4
        blc.add(new JPanel());

        add(blc, BorderLayout.CENTER);

        //BorderLayout의 South
        JPanel bls = new JPanel();
        bls.setLayout(new FlowLayout());
        bls.setBackground(Color.gray);

        loginbt = new JButton("LOGIN");
        loginbt.setPreferredSize(new Dimension(500, 100));
        loginbt.setFont(new DefaultFont(FONT_SIZE));
        bls.add(loginbt);

        loginbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkerDTO worker = parent.getController().getWorkerDAO().findById(idtf.getText());
                if(worker == null){

                }else if(!worker.getPasswd().equals(pwtf.getText())){

                }else{
                    parent.switchTop(DefaultFrame.TOP_ADMIN);
                    parent.resetMove(new ManagerMainView(parent, worker));
                }
            }
        });

        add(bls, BorderLayout.SOUTH);
    }
}
