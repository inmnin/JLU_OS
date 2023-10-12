package Peterson.Typical;

import java.awt.*;

public class Peterson_Typical_Mypanel1 extends Panel {
    int flag;
    public void setcolor(int flag){
        this.flag = flag;
    }
    public void paint(Graphics graphics){
        graphics.drawString("Process 1:",0,10);
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
