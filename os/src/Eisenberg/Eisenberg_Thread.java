package Eisenberg;

import java.util.Random;

public class Eisenberg_Thread implements Runnable{
    int id;
    Eisenberg_Page page;
    Eisenberg_Thread(int id,Eisenberg_Page page){
        this.id = id;
        this.page = page;
    }
    @Override
    public void run() {
        int index;
        do{
            page.flags[id] = page.WAITING;
            index = page.turn;
            while(index != id){
                if (page.flags[index] != page.IDLE)
                    index = page.turn;
                else{
                    index = (index+1) % page.NUM_THREADS;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            page.flags[id] = page.ACTIVE;
            index = 0;
            while(page.flags[index] != page.ACTIVE)
                index = (index+1) % page.NUM_THREADS;
            page.mid_text.append("进程"+(id+1)+"正在等待.....\n");
        }while(!(index == id && (page.turn == id || page.flags[page.turn] == page.IDLE)) );
        page.turn = id;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.mid_text.append("---进程"+(id+1)+"正在访问临界区---\n");
        page.mypanel.setRunning_id(id+1);
        Random ra = new Random();
        page.mypanel.repaint();
        try {
            Thread.sleep(1500+ra.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        index = (page.turn +1) % page.NUM_THREADS;
        while(page.flags[index] == page.IDLE){
            index = (index+1) % page.NUM_THREADS;
        }
        page.turn = index;

        page.mid_text.append("*****进程"+(id+1)+"访问结束！*****\n");
        page.mypanel.setRunning_id(id+1);
        page.mypanel.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.flags[id] = page.IDLE;


    }
}
