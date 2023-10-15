package PageMgr;

import Overwatching.OutPut;
import Visualization.Double_Thread_Mypanel;

import javax.swing.*;
import java.awt.*;

public class Double_Thread_Page extends Page{
    public Double_Thread_Mypanel[]mypanel;
    public JTextArea mid_text;
    public JScrollPane mid_scroll;
    public JTextArea right_text;
    public JScrollPane right_scroll;
    public JPanel left_block;
    public JPanel left_mid_block;
    public JButton return_button;
    public JTextArea left_title;
    public OutPut outPut;
    public Double_Thread_Page(){
        turn = 0;
        this.setSize(600,400);
        this.setLocation(400,150);
        this.setLayout(new BorderLayout());

        //面板中间显示谁正在运行的文本框
        mid_text = new JTextArea();
        mid_scroll = new JScrollPane(mid_text);
        mid_text.setEditable(false);
        mid_text.setAutoscrolls(true);
        this.add(mid_scroll,BorderLayout.CENTER);
        mid_scroll.setPreferredSize(new Dimension(this.getWidth()/3, 400));

        //右边的线程运行信息输出框
        right_text = new JTextArea();
        right_scroll = new JScrollPane(right_text);
        right_text.setEditable(false);
        right_text.setAutoscrolls(true);
        this.add(right_scroll,BorderLayout.EAST);
        right_scroll.setPreferredSize(new Dimension(this.getWidth()/3, 400)); // 例如，宽度为150px

        //左边面板及其相关的控件
        left_block = new JPanel(new BorderLayout());
        left_block.setSize(this.getWidth()/3,400);
        this.add(left_block,BorderLayout.WEST);

        left_title= new JTextArea();
        left_title.setEditable(false);
        left_title.setFont(new Font("隶书", Font.BOLD,25));
        left_title.setBackground(new Color(238,238,238));
        left_block.add(left_title, BorderLayout.NORTH);

        return_button = new JButton("返回");
        return_button.setBounds(10, left_block.getHeight()-35,40,30);
        left_block.add(return_button,BorderLayout.SOUTH);

        //左边小组件
        left_mid_block = new JPanel();
        left_mid_block.setSize(200,200);
        left_mid_block.setLayout(null);
        left_block.add(left_mid_block, BorderLayout.CENTER);

        mypanel = new Double_Thread_Mypanel[2];
        for(int i = 0;i<=1;i++){
            mypanel[i] = new Double_Thread_Mypanel();
            mypanel[i].id = i;
        }
        mypanel[1].setBounds(0,150,150,150);
        mypanel[0].setBounds(0,0,150,150);
        left_mid_block.add(mypanel[0]);
        left_mid_block.add(mypanel[1]);


        this.setVisible(true);
        this.pack();
    }
}
