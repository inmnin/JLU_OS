package Dekker.General;

import PageMgr.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dekker_General_Page extends Page {

    public volatile boolean[] flag = null;
    int want_in;
    int NUM_THREADS;


    //mid件
    public JTextArea mid_text;
    public JScrollPane mid_scroll;
    //left件
    public JPanel left_state_block;
    public JPanel left_mid_block;
    public JButton return_button;

    //right件
    public JTextArea right_text;
    public JScrollPane right_scroll;

    //状态图形
    public Dekker_General_Mypanel mypanel;

    public Dekker_General_Page(int n){
        flag = new boolean[n+1];
        turn = 1; //创建的线程编号是从1开始的
        NUM_THREADS = n;
        for(int i=1;i<=n;i++){
            flag[i] = false;
        }

        //主块
        this.setSize(700,400);
        this.setLocation(400,150);
        this.setLayout(new BorderLayout());


        //中间状态栏
        mid_text = new JTextArea();
        mid_scroll = new JScrollPane(mid_text);
        mid_text.setEditable(false);
        mid_text.setAutoscrolls(true);
        mid_scroll.setPreferredSize(new Dimension(200, 400));
        this.add(mid_scroll,BorderLayout.CENTER);


        //左边图形状态栏
        left_state_block = new JPanel(new BorderLayout());
        this.add(left_state_block,BorderLayout.WEST);

        JTextArea left_title = new JTextArea();
        left_title.setEditable(false);
        left_title.setFont(new Font("隶书", Font.BOLD,25));
        left_title.append("   推广的Dekker算法   ");
        left_title.setBackground(new Color(238,238,238));
        left_state_block.add(left_title, BorderLayout.NORTH);

        left_mid_block = new JPanel();
        left_mid_block.setSize(200,200 + NUM_THREADS/6 * 50);
        left_mid_block.setLayout(null);
        left_state_block.add(left_mid_block, BorderLayout.CENTER);


        return_button = new JButton("返回");
        return_button.setBounds(10,left_state_block.getHeight()-35,40,30);
        return_button.addActionListener(new Return_Listenser());
        left_state_block.add(return_button,BorderLayout.SOUTH);


        //右边状态栏
        right_text = new JTextArea();
        right_scroll = new JScrollPane(right_text);
        right_text.setEditable(false);
        right_text.setAutoscrolls(true);
        right_scroll.setPreferredSize(new Dimension(200, 400));
        right_text.append("开始监控进程具体行为：\n");
        this.add(right_scroll,BorderLayout.EAST);




        mypanel = new Dekker_General_Mypanel(NUM_THREADS);
        mypanel.setBounds(0,0,200,300 + NUM_THREADS * 50);
        mypanel.setRunning_id(0);
        left_mid_block.add(mypanel);


        this.pack();
        this.setVisible(true);

    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Dekker_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Page.this.dispose();
            }
        }
    }


}
