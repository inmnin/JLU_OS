package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Dekker_Resource{
    public int[] flag= {0,0};
    public int number;
    public Mypanel011 mypanel011;
    public Mypanel010 mypanel010;
    public JDialog d = null;

    public JTextArea text;
    public JScrollPane scroll;
    public JTextArea output_text;
    public JScrollPane output_scroll;
    public JPanel D_panel01;
    public JPanel D_panel011;
    Dekker_Resource(JFrame f){
        Random ra =new Random();
        number =( ra.nextInt(10)+1) % 2;

        d = new JDialog(f);
        d.setSize(600,400);
        d.setLocation(900,200);
        d.setLayout(new BorderLayout());

        //面板中间显示谁正在运行的文本框
        text = new JTextArea();
        scroll = new JScrollPane(text);
        text.setEditable(false);
        text.setAutoscrolls(true);
        d.add(scroll,BorderLayout.CENTER);
        scroll.setPreferredSize(new Dimension(d.getWidth()/3, 400)); // 例如，宽度为150px

        //左边的线程运行信息输出框
        output_text = new JTextArea();
        output_scroll = new JScrollPane(output_text);
        output_text.setEditable(false);
        output_text.setAutoscrolls(true);
        d.add(output_scroll,BorderLayout.WEST);
        output_scroll.setPreferredSize(new Dimension(d.getWidth()/3, 400)); // 例如，宽度为150px

        //右边面板及其相关的控件
        D_panel01 = new JPanel(new BorderLayout());
        D_panel01.setSize(d.getWidth()/3,400);
        d.add(D_panel01,BorderLayout.EAST);

        JTextArea D_text = new JTextArea();
        D_text.setEditable(false);
        D_text.setFont(new Font("宋体", Font.BOLD,25));
        D_text.append("   Dekker算法   ");
        D_text.setBackground(new Color(238,238,238));
        D_panel01.add(D_text, BorderLayout.NORTH);

        D_panel011 = new JPanel();
        D_panel011.setSize(200,200);
        D_panel011.setLayout(null);
        D_panel01.add(D_panel011, BorderLayout.CENTER);

        mypanel011 = new Mypanel011();
        mypanel011.setBounds(0,150,150,150);

        mypanel010 = new Mypanel010();
        mypanel010.setBounds(0,0,150,150);
        d.setVisible(true);
    }
}

public class Dekker implements Runnable {
    private int id;
    private Dekker_Resource resource;

    Dekker(int id,Dekker_Resource resource){
        this.id = id;
        this.resource = resource;
    }
    @Override
    public void run() {
        resource.flag[id] = 1;

        resource.mypanel011.setcolor(1);
        resource.D_panel011.add(resource.mypanel011);

        resource.mypanel010.setcolor(1);
        resource.D_panel011.add(resource.mypanel010);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (resource.flag[1-id] != 0){
            resource.flag[id] = 0;
            while(resource.number != id){
                resource.text.append("process"+id+"正在等待...\n");
                if(id == 0){
                    resource.mypanel010.setcolor(1);
                    resource.mypanel010.repaint();
                }
                else {
                    resource.mypanel011.setcolor(1);
                    resource.mypanel011.repaint();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            resource.flag[id] = 1;
        }
        Random ra = new Random();
        resource.text.append("process"+id+"正在访问临界区----\n");
        if(id == 0){
            resource.mypanel010.setcolor(2);
            resource.mypanel010.repaint();
        }
        else {
            resource.mypanel011.setcolor(2);
            resource.mypanel011.repaint();
        }
        try {
            List<String> movement = new ArrayList<>(Arrays.asList("写入变量a", "写入变量b", "操作变量a和b", "修改变量a", "修改变量b",
                    "写入变量c", "释放变量a", "释放变量b", "操作变量c", "释放变量c"));

            resource.output_text.append("process"+id+"进入临界区并操作独占资源\n");
            int sleep_time = 2000+ra.nextInt(1000);
            for(int i = 0;i<=9;i++){
                //改变进程运行版信息
                resource.output_text.append(movement.get(i)+"\n");
                Thread.sleep(sleep_time/10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.number = 1-id;

        resource.text.append("process"+id+"访问结束！\n");
        if(id == 0){
            resource.mypanel010.setcolor(3);
            resource.mypanel010.repaint();
        }
        else {
            resource.mypanel011.setcolor(3);
            resource.mypanel011.repaint();
        }
        resource.flag[id] = 0;

    }
}

class Mypanel010 extends Panel{
    int flag1;
    public void setcolor(int flag1){
        this.flag1 = flag1;
    }
    public void paint(Graphics g){
        g.drawString("process0:",0,10);
        switch (flag1){
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.YELLOW);
                break;
            case 3:
                g.setColor(Color.GREEN);
        }
        g.drawOval(20,10,50,50);
        g.fillOval(20,10,50,50);
    }
}

class Mypanel011 extends Panel{
    int flag1;
    public void setcolor(int flag1){
        this.flag1 = flag1;
    }
    public void paint(Graphics g){
        g.drawString("process1:",0,10);
        switch (flag1){
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.YELLOW);
                break;
            case 3:
                g.setColor(Color.GREEN);
        }
        g.drawOval(20,10,50,50);
        g.fillOval(20,10,50,50);
    }
}


