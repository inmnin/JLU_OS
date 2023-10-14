package Dekker.General;

import Lamport.Lamport_Page;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dekker_General_Thread implements Runnable{
    int id;
    Dekker_General_Page page;

    public Dekker_General_Thread(int id, Dekker_General_Page page)  {
        this.id = id;
        this.page = page;
    }

    @Override
    public void run() {

        // 我想进入
        synchronized (page){
            page.flag[id] = true;
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(true) {
                synchronized (page) {
                    boolean can_in = true;
                    for (int i = 1; i <= page.NUM_THREADS; i++) {
                        if (i != id) {
                            if (page.flag[i]) {
                                page.flag[id] = false;
                                can_in = false;
                            }
                        }
                    }
                    if(can_in == true) break;
                }

            int maxTries = 3;  // 设置最大尝试次数为5，这意味着线程最多会等待10秒
            int timeoutCounter = 0;
            while(page.turn != id && timeoutCounter<maxTries){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        page.mid_text.append("process"+id+"正在等待...\n");
                    }
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeoutCounter++;
                }
            synchronized (page)
            {page.flag[id] = true;}
        }


            page.mid_text.append("--进程" + id + "正在访问临界区--\n");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Random random = new Random();
            page.mypanel.setRunning_id(id);
            page.mypanel.repaint();
            try {
                int sleep_time = (1500 + random.nextInt(500)) * 5;
                List<String> movement = new ArrayList<>(Arrays.asList("写入变量a", "写入变量b", "操作变量a和b", "修改变量a", "修改变量b",
                        "写入变量c", "释放变量a", "释放变量b", "操作变量c", "释放变量c"));

                page.right_text.append("process" + id + "进入临界区并操作独占资源\n");
                for (int i = 0; i <= 9; i++) {
                    //改变左边进程运行版信息
                    page.right_text.append(movement.get(i) + "\n");
                    Thread.sleep(sleep_time / 10);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            page.mid_text.append("*****进程" + id + "访问结束！*****\n");

            //绘制绿色图标
            page.mypanel.setRunning_id(id);
            page.mypanel.repaint();

            //暂停一会儿
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //让出turnSD

            synchronized (page) {
                page.turn = id % page.NUM_THREADS + 1; // 将turn设置为下一个线程
                page.flag[id] = false; // 退出临界区
            }
    System.out.println();
    }
}