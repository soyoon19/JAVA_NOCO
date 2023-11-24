package custom_component;

import javax.swing.*;
import java.awt.*;

public class JPanelOneLabel extends JPanel {
    private JLabel label;
    public JPanelOneLabel(String name){
        label = new JLabel(name);
        this.add(label);
    }

    public JPanelOneLabel(String name, LayoutManager layout){
        this(name);
        this.setLayout(layout);
    }

    public JLabel getLabel(){
        return label;
    }
}
