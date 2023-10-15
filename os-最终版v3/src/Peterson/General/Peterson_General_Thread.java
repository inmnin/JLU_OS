package Peterson.General;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.SwingUtilities;

public class Peterson_General_Thread implements Runnable{
    int id;
    Peterson_General_Page page;
    Peterson_Filter mylock;
    public Peterson_General_Thread(int id, Peterson_General_Page page, Peterson_Filter filter){
        this.id = id;
        this.page = page;
        mylock = filter;
    }
    public void run(){

        //先停顿一会儿
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //有人想取号我就稍后，但是由于并发控制会出现两人取到同号的情况

        mylock.lock(id);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                page.mid_text.append("--进程"+id+"正在访问临界区--\n");
            }
        });


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

        //接触受害者锁
        mylock.unlock(id);

    }

}