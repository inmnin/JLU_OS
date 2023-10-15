package Peterson.General;

import Lamport.Lamport_Page;
import PageMgr.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Visualization.*;

public class Peterson_General_Page extends Page {

    int NUM_THREADS;

    //mid件
    public JTextArea mid_text;
    public JScrollPane mid_scroll;
    //right件
    public JPanel left_block;
    public JPanel left_mid_block;

    //right件
    public JTextArea right_text;
    public JScrollPane right_scroll;

    //状态图形
    public Multi_Thread_Mypanel mypanel;
    public Filter_Monitor mymonitor;

    public JButton return_button;

    
    public Peterson_General_Page(int n){

        NUM_THREADS = n;
        this.setSize(700,600);
        this.setLocation(400,150);
        this.setLayout(new BorderLayout());


        //中间状态栏
        mid_text = new JTextArea();
        mid_scroll = new JScrollPane(mid_text);
        mid_text.setEditable(false);
        mid_text.setAutoscrolls(true);
        mid_scroll.setPreferredSize(new Dimension(230, 400));
        this.add(mid_scroll,BorderLayout.CENTER);

//        //左边状态栏
//        mid_text = new JTextArea();
//        mid_scroll = new JScrollPane(mid_text);
//        mid_text.setEditable(false);
//        mid_text.setAutoscrolls(true);
//        this.add(mid_scroll,BorderLayout.CENTER);


        //左边图形状态栏
        left_block = new JPanel(new BorderLayout());
        this.add(left_block,BorderLayout.WEST);

        JTextArea left_title = new JTextArea();
        left_title.setEditable(false);
        left_title.setFont(new Font("隶书", Font.BOLD,25));
        left_title.append("   推广Peterson算法   ");
        left_title.setBackground(new Color(238,238,238));
        left_block.add(left_title, BorderLayout.NORTH);

        left_mid_block = new JPanel();
        left_mid_block.setSize(200,500 + NUM_THREADS/6 * 50);
        left_mid_block.setLayout(null);
        left_block.add(left_mid_block, BorderLayout.CENTER);


        return_button = new JButton("返回");
        return_button.setBounds(10, left_block.getHeight()-35,40,30);
        return_button.addActionListener(new Peterson_General_Page.Return_Listenser());
        left_block.add(return_button,BorderLayout.SOUTH);

        //右边状态栏
        right_text = new JTextArea();
        right_scroll = new JScrollPane(right_text);
        right_text.setEditable(false);
        right_text.setAutoscrolls(true);
        right_scroll.setPreferredSize(new Dimension(200, 400));
        right_text.append("开始监控进程具体行为：\n");
        this.add(right_scroll,BorderLayout.EAST);




        mypanel = new Multi_Thread_Mypanel(NUM_THREADS);
        mypanel.setBounds(0,0,200,50 + NUM_THREADS * 5);
        mypanel.setRunning_id(0);
        mypanel.setVisible(true);
        left_mid_block.add(mypanel);


        this.pack();
        this.setVisible(true);

    }
    public void setmonitor(){
        left_mid_block.add(mymonitor);
        mymonitor.setBounds(5,180,200,220);
        JTextArea left_subtitle = new JTextArea();
        left_subtitle.setEditable(false);
        left_subtitle.setFont(new Font("隶书", Font.BOLD,25));
        left_subtitle.append("   监督Filter锁   ");
        left_subtitle.setBackground(new Color(238,238,238));
        left_subtitle.setBounds(0,mymonitor.getY()-25,200,26);
        left_mid_block.add(left_subtitle);
    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Peterson_General_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_General_Page.this.dispose();
            }
        }
    }

}
