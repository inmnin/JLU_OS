package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class Lamport_Resource{
    boolean Entering[] = null;

    int Number[] = null;
    int NUM_THREADS;
    int visit = 0;

    public JDialog d = null;
    public JTextArea text;
    public JScrollPane scroll01;
    public JScrollPane scroll02;
    public JPanel L_panel01;
    public JPanel L_panel011;
    public Mypanel02 mypanel02;

    Lamport_Resource(int n,JFrame f){
        Entering = new boolean[n+1];
        Number = new int[n+1];
        NUM_THREADS = n;
        for(int i=1;i<=n;i++){
            Entering[i] = false;

            Number[i] = 0;
        }
        d = new JDialog(f);
        d.setSize(400,400);
        d.setLocation(900,200);
        d.setLayout(new BorderLayout());
        text = new JTextArea();
        scroll01 = new JScrollPane(text);

        text.setEditable(false);
        text.setAutoscrolls(true);
        d.add(scroll01,BorderLayout.CENTER);

        L_panel01 = new JPanel(new BorderLayout());
        L_panel01.setSize(200,400);
        d.add(L_panel01,BorderLayout.EAST);

        JTextArea L_text = new JTextArea();
        L_text.setEditable(false);
        L_text.setFont(new Font("宋体", Font.BOLD,25));
        L_text.append("   Lamport算法   ");
        L_text.setBackground(new Color(238,238,238));
        L_panel01.add(L_text, BorderLayout.NORTH);

        L_panel011 = new JPanel();
        L_panel011.setSize(200,200 + NUM_THREADS/6 * 50);
        L_panel011.setLayout(null);
        L_panel01.add(L_panel011, BorderLayout.CENTER);

        mypanel02 = new Mypanel02(NUM_THREADS);
        mypanel02.setBounds(0,0,200,300 + NUM_THREADS * 50);
        mypanel02.setChange_id(0);
        L_panel011.add(mypanel02);



        d.setVisible(true);

    }
}



public class Lamport implements Runnable{
    int id;
    Lamport_Resource resource;
    Lamport(int id,Lamport_Resource resource){
        this.id = id;
        this.resource = resource;
    }
    public void run(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        resource.Entering[id] = true;
        resource.Number[id] = max() + 1;
        resource.Entering[id] = false;
        for (int j=1;j<=resource.NUM_THREADS;j++){
            while(resource.Entering[j]){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.text.append("---process正在取号---\n");
            }
            while((resource.Number[j] != 0) && ((resource.Number[j] < resource.Number[id]) || ((resource.Number[j] == resource.Number[id]) &&(j < id)))){
                int max=3000,min=2000;
                int ran1 = (int) (Math.random()*(max-min)+min);
                try {
                    Thread.sleep(ran1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                resource.text.append("process"+id+"正在等待....\n");
            }
        }

        resource.text.append("--process"+id+"正在访问临界区--\n");
        Random ra = new Random();
        resource.mypanel02.setChange_id(id);
        resource.mypanel02.repaint();
        try {
            Thread.sleep(1500+ra.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.text.append("*****process"+id+"访问结束！*****\n");
        resource.mypanel02.setChange_id(id);
        resource.mypanel02.repaint();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.Number[id] = 0;

    }
    private int max(){
        int max = -1;
        for(int i=1;i<=resource.NUM_THREADS;i++){
            if(max < resource.Number[i])
                max = resource.Number[i];
        }
        return max;
    }

}




class Mypanel02 extends Panel {

    int NUM_THREAD;
    int change_id;
    boolean ifyellow[] = null;
    boolean ifgreen[] = null;
    boolean ifred[] = null;
    Mypanel02(int NUM_THREAD){
        this.NUM_THREAD = NUM_THREAD;
        this.ifyellow = new boolean[NUM_THREAD];
        this.ifgreen = new boolean[NUM_THREAD];
        this.ifred = new boolean[NUM_THREAD];
        for (int i = 0; i < NUM_THREAD; i++) {
            this.ifyellow[i] = false;
            this.ifgreen[i] = false;
            this.ifred[i] = true;
        }
    }
    public void setChange_id(int change_id) {
        this.change_id = change_id;
    }

    public void paint(Graphics g) {
        for(int i = 0;i < NUM_THREAD; i++){
            g.setColor(Color.BLACK);
            g.drawOval((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
            String s = String.valueOf(i+1);
            g.drawString(s,(i % 6) * 30 + 10, (i / 6) * 50 + 10);
            if(ifred[i] && (change_id - 1) != i){                                       //红不变
                g.setColor(Color.RED);
                g.fillOval((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                continue;
            }
            if(ifred[i] && (change_id - 1) == i){                                        //红变黄
                g.setColor(Color.YELLOW);
                g.fillOval((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                ifred[i] = false;
                ifyellow[i] = true;
                continue;
            }
            if(ifyellow[i] && (change_id - 1) != i){                                    //黄不变
                g.setColor(Color.YELLOW);
                g.fillOval((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                continue;
            }
            if(ifyellow[i] && (change_id - 1) == i){                                    //黄变绿
                g.setColor(Color.GREEN);
                g.fillOval((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                ifyellow[i] = false;
                ifgreen[i] = true;
                continue;
            }
            if(ifgreen[i]){
                g.setColor(Color.GREEN);
                g.fillOval((i % 6) * 30 + 10, (i / 6) * 50 + 10, 25, 25);
                continue;
            }


        }

    }
}



