package Peterson.Typical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.SwingUtilities;



public class Peterson_Typical_Thread implements Runnable{
    private int id;
    private Peterson_Typical_Page page;

    public Peterson_Typical_Thread(int id, Peterson_Typical_Page page){
        this.id = id;
        this.page = page;
    }
    @Override
    public void run() {


        page.flag[id] = true;

        //出让优先权
        page.turn = 1-id;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(page.flag[1-id] && page.turn == 1-id){
            page.mid_text.append("进程"+id+"正在等待...\n");
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                page.mid_text.append("进程"+id+"正在访问临界区----\n");
            }
        });

        Random ra = new Random();
        if(id == 0){
            page.mypanel0.setcolor(2);
            page.mypanel1.repaint();
        }
        else {
            page.mypanel0.setcolor(2);
            page.mypanel1.repaint();
        }
        try {
            List<String> movement = new ArrayList<>(Arrays.asList("写入变量a", "写入变量b", "操作变量a和b", "修改变量a", "修改变量b",
                    "写入变量c", "释放变量a", "释放变量b", "操作变量c", "释放变量c"));

            page.right_text.append("process"+id+"进入临界区并操作独占资源\n");
            int sleep_time = 2000+ra.nextInt(1000);
            for(int i = 0;i<=9;i++){
                //改变进程运行版信息
                page.right_text.append(movement.get(i)+"\n");
                Thread.sleep(sleep_time/10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.flag[id] = false;

        page.mid_text.append("进程"+id+"访问结束！\n");
        if(id == 0){
            page.mypanel0.setcolor(3);
            page.mypanel0.repaint();
        }
        else {
            page.mypanel1.setcolor(3);
            page.mypanel1.repaint();
        }
    }
}