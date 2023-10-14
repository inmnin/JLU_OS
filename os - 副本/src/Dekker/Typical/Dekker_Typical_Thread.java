package Dekker.Typical;

import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class Dekker_Typical_Thread implements Runnable {
    private int id;
    private Dekker_Typical_Page page;

    public Dekker_Typical_Thread(int id, Dekker_Typical_Page page){
        this.id = id;
        this.page = page;
    }
    @Override
    public void run() {
        //我想进入
        page.flag[id] = 1;

        //初始化红绿灯状态
        page.mypanel0.setcolor(1);
        page.left_mid_panel.add(page.mypanel0);
        page.mypanel1.setcolor(1);
        page.left_mid_panel.add(page.mypanel1);

        //小睡一会儿
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //循环谦让
        while (page.flag[1-id] != 0){
            //谦让
            page.flag[id] = 0;
            //等对面进入临界区
            while(page.turn != id){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        page.mid_text.append("process"+id+"正在等待...\n");
                    }
                });
                if(id == 0){
                    page.mypanel0.setcolor(1);
                    page.mypanel0.repaint();
                }
                else {
                    page.mypanel1.setcolor(1);
                    page.mypanel1.repaint();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            page.flag[id] = 1;
        }

        //进入临界区
        Random ra = new Random();
        page.mid_text.append("process"+id+"正在访问临界区----\n");
        if(id == 0){
            page.mypanel0.setcolor(2);
            page.mypanel0.repaint();
        }
        else {
            page.mypanel1.setcolor(2);
            page.mypanel1.repaint();
        }

        //模拟一系列临界区操作，可视化证明退出临界区前别的进程无法进入临界区
        try {
            List<String> movement = new ArrayList<>(Arrays.asList("写入变量a", "写入变量b", "操作变量a和b", "修改变量a", "修改变量b",
                    "写入变量c", "释放变量a", "释放变量b", "操作变量c", "释放变量c"));

            page.right_text.append("process"+id+"进入临界区并操作独占资源\n");
            int sleep_time = 2000+ra.nextInt(1000);
            for(int i = 0;i<=9;i++){
                //改变左边进程运行版信息
                page.right_text.append(movement.get(i)+"\n");
                Thread.sleep(sleep_time/10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //退出临界区后该出让优先级了
        page.turn = 1-id;

        page.mid_text.append("process"+id+"访问结束！\n");
        if(id == 0){
            page.mypanel0.setcolor(3);
            page.mypanel0.repaint();
        }
        else {
            page.mypanel1.setcolor(3);
            page.mypanel1.repaint();
        }
        //抹去访问申请标记
        page.flag[id] = 0;
    }
}


