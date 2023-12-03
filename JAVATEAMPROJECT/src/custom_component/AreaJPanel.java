package custom_component;

import javax.swing.*;

public class AreaJPanel extends JPanel {
    private int i, j;
    private boolean use = true;

    public AreaJPanel(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public boolean getUse() {
        return use;
    }
}
