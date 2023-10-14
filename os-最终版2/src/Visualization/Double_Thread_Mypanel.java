package Visualization;



import java.awt.*;

public class Double_Thread_Mypanel extends Panel{
    int flag;
    public void setcolor(int flag){
        this.flag = flag;
    }
    public void paint(Graphics g){
        g.drawString(" process0:",0,10);
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

        //在right_mid_panel中的相对位置
        g.drawOval(20,10,50,50);
        g.fillOval(20,10,50,50);
    }
}
