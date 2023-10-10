package Peterson;

import javax.swing.*;
import java.awt.*;

public class Peterson_Mypanel0 extends JPanel {
    int flag;
    public void setcolor(int flag){
        this.flag = flag;
    }
    public void paint(Graphics graphics){
        graphics.drawString("Process 0:",0,10);
        switch (flag){
            case 1:
                graphics.setColor(Color.RED);
                break;
            case 2:
                graphics.setColor(Color.YELLOW);
                break;
            case 3:
                graphics.setColor(Color.GREEN);
        }
        graphics.drawOval(20,10,50,50);
        graphics.fillOval(20,10,50,50);
    }
}
