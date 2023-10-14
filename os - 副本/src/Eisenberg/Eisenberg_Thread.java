package Eisenberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Eisenberg_Thread implements Runnable {
    int id;
    Eisenberg_Page page;

    Eisenberg_Thread(int id, Eisenberg_Page page) {
        this.id = id;
        this.page = page;
    }

    @Override
    public void run() {
        int index;
        do {
            page.flags[id] = page.WAITING;
            index = page.turn;
            while (index != id) {
                if (page.flags[index] != page.IDLE)
                    index = page.turn;
                else {
                    index = (index % page.NUM_THREADS) + 1; // Mod adjustment
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            page.flags[id] = page.ACTIVE;
            index = 1; // Start from 1
            while (page.flags[index] != page.ACTIVE)
                index = (index % page.NUM_THREADS) + 1; // Mod adjustment
            page.mid_text.append("进程" + id + "正在等待.....\n");
        } while (!(index == id && (page.turn == id || page.flags[page.turn] == page.IDLE)));
        page.turn = id;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.mid_text.append("---进程" + id + "正在访问临界区---\n");
        page.mypanel.setRunning_id(id);
        Random random = new Random();
        page.mypanel.repaint();
        try {
            int sleep_time = (1500+random.nextInt(500))*5;
            List<String> movement = new ArrayList<>(Arrays.asList("写入变量a", "写入变量b", "操作变量a和b", "修改变量a", "修改变量b",
                    "写入变量c", "释放变量a", "释放变量b", "操作变量c", "释放变量c"));

            page.right_text.append("process"+id+"进入临界区并操作独占资源\n");
            for(int i = 0;i<=9;i++){
                //改变左边进程运行版信息
                page.right_text.append(movement.get(i)+"\n");
                Thread.sleep(sleep_time/10);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        index = (page.turn % page.NUM_THREADS) + 1; // Mod adjustment
        while (page.flags[index] == page.IDLE) {
            index = (index % page.NUM_THREADS) + 1; // Mod adjustment
        }
        page.turn = index;

        page.mid_text.append("*****进程" + id + "访问结束！*****\n");
        page.mypanel.setRunning_id(id);
        page.mypanel.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.flags[id] = page.IDLE;
    }
}
