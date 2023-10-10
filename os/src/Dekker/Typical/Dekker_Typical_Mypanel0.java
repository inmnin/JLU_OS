package Dekker.Typical;


import java.awt.*;

public class Dekker_Typical_Mypanel0 extends Panel{
    int flag1;
    public void setcolor(int flag1){
        this.flag1 = flag1;
    }
    public void paint(Graphics g){
        g.drawString(" process0:",0,10);
        switch (flag1){
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.YELLOW);
                break;
            case 3:
                g.setColor(Color.GREEN);
        }

        //在right_mid_panel中的相对位置
        g.drawOval(20,10,50,50);
        g.fillOval(20,10,50,50);
    }
}
