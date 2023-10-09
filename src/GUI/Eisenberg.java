package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Eisenberg_Resource{
    int flags[] = null;
    int NUM_THREADS;
    int IDLE = 0;
    int WAITING = 1;
    int ACTIVE = 2;
    int turn;

    public JDialog d = null;
    public JTextArea text;
    public JScrollPane scroll;
    public JPanel L_panel01;
    public JPanel L_panel011;
    public Mypanel02 mypanel02;

    Eisenberg_Resource(int n, JFrame f){
        flags = new int[n];
        for(int i=0;i<n;i++)
            flags[i] = IDLE;
        NUM_THREADS = n;
        Random random = new Random();
        turn = random.nextInt(n);

        d = new JDialog(f);
        d.setSize(400,400);
        d.setLocation(900,200);
        d.setLayout(new BorderLayout());
        text = new JTextArea();
        scroll = new JScrollPane(text);
        text.setEditable(false);
        text.setAutoscrolls(true);
        d.add(scroll,BorderLayout.CENTER);

        L_panel01 = new JPanel(new BorderLayout());
        L_panel01.setSize(200,400);
        d.add(L_panel01,BorderLayout.EAST);

        JTextArea L_text = new JTextArea();
        L_text.setEditable(false);
        L_text.setFont(new Font("宋体", Font.BOLD,25));
        L_text.append("   Eisenberg算法   ");
        L_text.setBackground(new Color(238,238,238));
        L_panel01.add(L_text, BorderLayout.NORTH);

        L_panel011 = new JPanel();
        L_panel011.setSize(200,200);
        L_panel011.setLayout(null);
        L_panel01.add(L_panel011, BorderLayout.CENTER);

        mypanel02 = new Mypanel02(NUM_THREADS);
        mypanel02.setBounds(0,0,200, 300+NUM_THREADS * 5);
        mypanel02.setChange_id(0);
        L_panel011.add(mypanel02);

        d.setVisible(true);

    }
}



public class Eisenberg implements Runnable{
    int id;
    Eisenberg_Resource resource;
    Eisenberg(int id,Eisenberg_Resource resource){
        this.id = id;
        this.resource = resource;
    }
    @Override
    public void run() {
        int index;
        do{
            resource.flags[id] = resource.WAITING;
            index = resource.turn;
            while(index != id){
                if (resource.flags[index] != resource.IDLE)
                    index = resource.turn;
                else{
                    index = (index+1) % resource.NUM_THREADS;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            resource.flags[id] = resource.ACTIVE;
            index = 0;
            while(resource.flags[index] != resource.ACTIVE)
                index = (index+1) % resource.NUM_THREADS;
            resource.text.append("进程"+(id+1)+"正在等待.....\n");
        }while(!(index == id && (resource.turn == id || resource.flags[resource.turn] == resource.IDLE)) );
        resource.turn = id;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.text.append("---进程"+(id+1)+"正在访问临界区---\n");
        resource.mypanel02.setChange_id(id+1);
        Random ra = new Random();
        resource.mypanel02.repaint();
        try {
            Thread.sleep(1500+ra.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        index = (resource.turn +1) % resource.NUM_THREADS;
        while(resource.flags[index] == resource.IDLE){
            index = (index+1) % resource.NUM_THREADS;
        }
        resource.turn = index;

        resource.text.append("*****进程"+(id+1)+"访问结束！*****\n");
        resource.mypanel02.setChange_id(id+1);
        resource.mypanel02.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.flags[id] = resource.IDLE;


    }
}



