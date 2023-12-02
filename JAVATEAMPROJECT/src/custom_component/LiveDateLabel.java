package custom_component;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class LiveDateLabel extends Thread{
    JLabel target;
    SimpleDateFormat format;
    private boolean stop = true;

    public LiveDateLabel(JLabel target, SimpleDateFormat format){
        this.target = target;
        this.format = format;
    }

    public void run(){
        stop = false;
        while(!stop) {
            target.setText(format.format(System.currentTimeMillis()));

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void ThreadStop(){
        stop = true;
    }

}
