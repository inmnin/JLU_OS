package Eisenberg;


import java.awt.*;

public class Eisenberg_Mypanel extends Panel {
    int NUM_THREAD;
    int running_id;
    boolean if_yellow[] = null;
    boolean if_green[] = null;
    boolean if_red[] = null;
    Eisenberg_Mypanel(int NUM_THREAD){
        this.NUM_THREAD = NUM_THREAD;
        this.if_yellow = new boolean[NUM_THREAD];
        this.if_green = new boolean[NUM_THREAD];
        this.if_red = new boolean[NUM_THREAD];
        for (int i = 0; i < NUM_THREAD; i++) {
            this.if_yellow[i] = false;
            this.if_green[i] = false;
            this.if_red[i] = true;
        }
    }
    public void setRunning_id(int running_id) {
        this.running_id = running_id;
    }

    public void paint(Graphics graphics) {
        for(int i = 0;i < NUM_THREAD; i++){
            graphics.setColor(Color.BLACK);

            graphics.drawRect((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
            String s = String.valueOf(i+1);
            graphics.drawString(s,(i % 6) * 30 + 10, (i / 6) * 50 + 10);

            if(if_red[i] && (running_id - 1) != i){                                       //红不变
                graphics.setColor(Color.RED);
                graphics.fillRect((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                continue;
            }
            if(if_red[i] && (running_id - 1) == i){                                        //红变黄
                graphics.setColor(Color.YELLOW);
                graphics.fillRect((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                if_red[i] = false;
                if_yellow[i] = true;
                continue;
            }
            if(if_yellow[i] && (running_id - 1) != i){                                    //黄不变
                graphics.setColor(Color.YELLOW);
                graphics.fillRect((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                continue;
            }
            if(if_yellow[i] && (running_id - 1) == i){                                    //黄变绿
                graphics.setColor(Color.GREEN);
                graphics.fillRect((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                if_yellow[i] = false;
                if_green[i] = true;
                continue;
            }
            if(if_green[i]){
                graphics.setColor(Color.GREEN);
                graphics.fillRect((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                continue;
            }


        }

    }
}
