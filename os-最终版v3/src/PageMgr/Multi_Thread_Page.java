package PageMgr;

import Overwatching.OutPut;
import Visualization.Multi_Thread_Mypanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Multi_Thread_Page extends Page{

    public int NUM_THREADS;
    public JTextArea mid_text;
    public JScrollPane mid_scroll;
    public JPanel left_block;
    public JPanel left_mid_block;
    public JTextArea right_text;
    public JScrollPane right_scroll;
    public JButton return_button;
    public JTextArea title;

    public Multi_Thread_Mypanel mypanel;
    public OutPut outPut;
    public Multi_Thread_Page(int n,String tmp_title){

        NUM_THREADS = n;
        Random random = new Random();
        turn = 1;

        this.setSize(700,400);
        this.setLocation(400,150);
        this.setLayout(new BorderLayout());

        mid_text = new JTextArea();
        mid_scroll = new JScrollPane(mid_text);
        mid_text.setEditable(false);
        mid_text.setAutoscrolls(true);
        mid_scroll.setPreferredSize(new Dimension(250, 400));
        this.add(mid_scroll,BorderLayout.CENTER);

        left_block = new JPanel(new BorderLayout());
        left_block.setSize(200,400);
        this.add(left_block,BorderLayout.WEST);

        JTextArea title = new JTextArea();
        title.setEditable(false);
        title.setFont(new Font("隶书", Font.BOLD,25));
        title.append(tmp_title);
        title.setBackground(new Color(238,238,238));
        left_block.add(title, BorderLayout.NORTH);

        left_mid_block = new JPanel();
        left_mid_block.setSize(200,200);
        left_mid_block.setLayout(null);
        left_block.add(left_mid_block, BorderLayout.CENTER);


        return_button = new JButton("返回");
        return_button.setBounds(10,left_block.getHeight()-35,40,30);
        left_block.add(return_button,BorderLayout.SOUTH);


        right_text = new JTextArea();
        right_scroll = new JScrollPane(right_text);
        right_text.setEditable(false);
        right_text.setAutoscrolls(true);
        right_scroll.setPreferredSize(new Dimension(250, 400));
        right_text.append("开始监控进程具体行为：\n");
        this.add(right_scroll,BorderLayout.EAST);


        mypanel = new Multi_Thread_Mypanel(NUM_THREADS);
        mypanel.setBounds(0,0,200, 300+NUM_THREADS * 5);
        mypanel.setRunning_id(0);
        left_mid_block.add(mypanel);


        this.pack();
        this.setVisible(true);
    }
}
