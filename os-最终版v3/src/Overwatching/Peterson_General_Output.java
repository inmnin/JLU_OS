package Overwatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Peterson_General_Output extends OutPut{
    public void waiting_output(int id){
        mid_text.append("--进程" + id + "成为受害者 且被拒绝进入临界区\n");
        mid_text.setCaretPosition(mid_text.getDocument().getLength());
    }
    public void ready_output(int id){

        mid_text.append("\n--进程" + id + "正在访问临界区--\n");
        mid_text.setCaretPosition(mid_text.getDocument().getLength());
    }
    public void visiting_output(int id){

        Random random = new Random();
        String bef_info = "线程"+id+": ";

        try {
            int sleep_time = (1500 + random.nextInt(500)) * 5;
            List<String> movement = new ArrayList<>(Arrays.asList(bef_info+"写入变量a", bef_info+"写入变量b", bef_info+"操作变量a和b", bef_info+"修改变量a",bef_info + "修改变量b",
                    bef_info+"写入变量c", bef_info+"释放变量a", bef_info+"释放变量b", bef_info+"操作变量c", bef_info+"释放变量c"));

            right_text.append("process" + id + "进入临界区并操作独占资源\n");
            right_text.setCaretPosition(right_text.getDocument().getLength());
            for (int i = 0; i <= 9; i++) {
                //改变左边进程运行版信息
                right_text.append(movement.get(i) + "\n");
                right_text.setCaretPosition(right_text.getDocument().getLength());
                Thread.sleep(sleep_time / 10);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finishing_output(int id){
        mid_text.append("*****进程" + id + "访问结束！*****\n");
        mid_text.setCaretPosition(mid_text.getDocument().getLength());
    }
}
