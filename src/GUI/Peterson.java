package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Peterson_Resource{
    public boolean[] flag={false,false};
    public int turn;
    public Mypanel021 mypanel021;
    public Mypanel020 mypanel020;
    public JDialog d = null;
    public JTextArea text;
    public JScrollPane scroll;
    public JTextArea output_text;
    public JScrollPane output_scroll;

    public JPanel P_panel01;
    public JPanel P_panel011;
    Peterson_Resource(JFrame f){
        d = new JDialog(f);
        d.setSize(600,400);
        d.setLocation(900,200);
        d.setLayout(new BorderLayout());

        //中间谁在运行
        text = new JTextArea();
        scroll = new JScrollPane(text);
        text.setEditable(false);
        text.setAutoscrolls(true);
        d.add(scroll,BorderLayout.CENTER);
        scroll.setPreferredSize(new Dimension(d.getWidth()/3, 400)); // 例如，宽度为150px


        //右边进程运行状态
        P_panel01 = new JPanel(new BorderLayout());
        P_panel01.setSize(d.getWidth()/3,400);
        d.add(P_panel01,BorderLayout.EAST);

        //左边的线程运行信息输出框
        output_text = new JTextArea();
        output_scroll = new JScrollPane(output_text);
        output_text.setEditable(false);
        output_text.setAutoscrolls(true);
        d.add(output_scroll,BorderLayout.WEST);
        output_scroll.setPreferredSize(new Dimension(d.getWidth()/3, 400)); // 例如，宽度为150px



        JTextArea P_text = new JTextArea();
        P_text.setEditable(false);
        P_text.setFont(new Font("宋体", Font.BOLD,25));
        P_text.append("   Peterson算法   ");
        P_text.setBackground(new Color(238,238,238));
        P_panel01.add(P_text, BorderLayout.NORTH);

        P_panel011 = new JPanel();
        P_panel011.setSize(d.getWidth()/3,200);
        P_panel011.setLayout(null);
        P_panel01.add(P_panel011, BorderLayout.CENTER);

        mypanel021 = new Mypanel021();
        mypanel021.setBounds(0,150,150,150);

        mypanel020 = new Mypanel020();
        mypanel020.setBounds(0,0,150,150);
        d.setVisible(true);
    }
}

public class Peterson implements Runnable{
    private int id;
    private Peterson_Resource resource;

    Peterson(int id,Peterson_Resource resource){
        this.id = id;
        this.resource = resource;
}
    @Override
    public void run() {
        resource.mypanel021.setcolor(1);
        resource.P_panel011.add(resource.mypanel021);

        resource.mypanel020.setcolor(1);
        resource.P_panel011.add(resource.mypanel020);

        resource.flag[id] = true;

        resource.turn = 1-id;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(resource.flag[1-id] && resource.turn == 1-id){
            resource.text.append("进程"+id+"正在等待...\n");
            if(id == 0){
                resource.mypanel020.setcolor(1);
                resource.mypanel020.repaint();
            }
            else {
                resource.mypanel021.setcolor(1);
                resource.mypanel021.repaint();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resource.text.append("进程"+id+"正在访问临界区----\n");
        Random ra = new Random();
        if(id == 0){
            resource.mypanel020.setcolor(2);
            resource.mypanel020.repaint();
        }
        else {
            resource.mypanel021.setcolor(2);
            resource.mypanel021.repaint();
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

        resource.flag[id] = false;

        resource.text.append("进程"+id+"访问结束！\n");
        if(id == 0){
            resource.mypanel020.setcolor(3);
            resource.mypanel020.repaint();
        }
        else {
            resource.mypanel021.setcolor(3);
            resource.mypanel021.repaint();
        }
    }
}

class Mypanel020 extends Panel{
    int flag1;
    public void setcolor(int flag1){
        this.flag1 = flag1;
    }
    public void paint(Graphics g){
        g.drawString("进程0:",0,10);
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

class Mypanel021 extends Panel{
    int flag1;
    public void setcolor(int flag1){
        this.flag1 = flag1;
    }
    public void paint(Graphics g){
        g.drawString("进程1:",0,10);
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

