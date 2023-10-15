package Visualization;


import java.awt.*;

public class Multi_Thread_Mypanel extends Panel {
    int NUM_THREAD;
    int running_id;
    boolean if_yellow[] = null;
    boolean if_green[] = null;
    boolean if_red[] = null;
    public Multi_Thread_Mypanel(int NUM_THREAD){
        this.NUM_THREAD = NUM_THREAD;
        this.if_yellow = new boolean[NUM_THREAD+1];
        this.if_green = new boolean[NUM_THREAD+1];
        this.if_red = new boolean[NUM_THREAD+1];
        for (int i = 1; i <= NUM_THREAD; i++) {
            this.if_yellow[i] = false;
            this.if_green[i] = false;
            this.if_red[i] = true;
        }
    }
    public void setRunning_id(int running_id) {
        this.running_id = running_id;
    }

    public void paint(Graphics graphics) {

        for(int i = 1;i <= NUM_THREAD; i++){
            int x = ((i-1)% 6) * 30 + 10;
            int y =((i-1) / 6) * 50 + 10;
            int width = 22;
            graphics.setColor(Color.BLACK);
            graphics.drawRect(x, y, width, width);
            String s = String.valueOf(i);
            graphics.drawString(s,x, y);

            if(if_red[i] && (running_id) != i){                                       //红不变
                graphics.setColor(Color.RED);
                graphics.fillRect(x, y, width, width);
                continue;
            }
            if(if_red[i] && (running_id) == i){                                        //红变黄
                graphics.setColor(Color.YELLOW);
                graphics.fillRect(x, y, width, width);
                if_red[i] = false;
                if_yellow[i] = true;
                continue;
            }
            if(if_yellow[i] && (running_id) != i){                                    //黄不变
                graphics.setColor(Color.YELLOW);
                graphics.fillRect(x, y, width, width);
                continue;
            }
            if(if_yellow[i] && (running_id) == i){                                    //黄变绿
                graphics.setColor(Color.GREEN);
                graphics.fillRect(x, y, width, width);
                if_yellow[i] = false;
                if_green[i] = true;
                continue;
            }
            if(if_green[i]){
                graphics.setColor(Color.GREEN);
                graphics.fillRect(x, y, width, width);
                continue;
            }


        }

    }
}
