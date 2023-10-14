package Lamport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lamport_Thread implements Runnable{
    int id;
    Lamport_Page page;
    public Lamport_Thread(int id,Lamport_Page page){
        this.id = id;
        this.page = page;
    }
    public void run(){

        //先停顿一会儿
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //取号过程
        page.Entering[id] = true;
        page.Number[id] = max() + 1;
        page.Entering[id] = false;

        //有人想取号我就稍后，但是由于并发控制会出现两人取到同号的情况
        for (int j = 1; j<= page.NUM_THREADS; j++){
            while(page.Entering[j]){
                //当然也可以忙式等待，这里直接采取睡眠一段时间的方式来节省资源
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                page.mid_text.append("有进程正在面包店取号\n");
            }

            //当有人比我号小或者我们同号但是它进程id小时，我就让出等待（这里用id号来判断其实是没啥道理的）
            //若走一圈所有人都没有这个情况（可能有人在我循环过去之后又取到号了，但是这个号一定要比我手里的号要小）
            while((page.Number[j] != 0) && ((page.Number[j] < page.Number[id]) || ((page.Number[j] == page.Number[id]) &&(j < id)))){
                //随机等待一个时间
                int max=3000,min=2000;
                int ran1 = (int) (Math.random()*(max-min)+min);
                try {
                    Thread.sleep(ran1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                page.mid_text.append("进程"+id+"想进入面包店却发现已经有人了\n");
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        page.mid_text.append("--进程"+id+"正在访问临界区--\n");

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        page.mypanel.setRunning_id(id);
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
        page.mid_text.append("*****进程"+id+"访问结束！*****\n");

        //绘制绿色图标
        page.mypanel.setRunning_id(id);
        page.mypanel.repaint();

        //暂停一会儿
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //注销面包店号
        page.Number[id] = 0;

    }

    //取号函数
    private int max(){
        int max = -1;
        for(int i = 1; i<= page.NUM_THREADS; i++){
            if(max < page.Number[i])
                max = page.Number[i];
        }
        return max;
    }

}