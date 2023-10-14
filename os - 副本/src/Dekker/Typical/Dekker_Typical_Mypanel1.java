package Dekker.Typical;

import java.awt.*;

public class Dekker_Typical_Mypanel1 extends Panel{
    int flag;
    public void setcolor(int flag){
        this.flag = flag;
    }
    public void paint(Graphics g){
        g.drawString(" process1:",0,10);
        switch (flag){
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.YELLOW);
                break;
            case 3:
                g.setColor(Color.GREEN);
        }
        g.drawOval(20,10,50,50);
        g.fillOval(20,10,50,50);
    }
}
