package Peterson.General;


import PageMgr.*;
import Peterson.Peterson_Option_Page;
import Visualization.Filter_Monitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Peterson_General_Option_Page extends Page {
    int THREAD_NUMS;
    public  JLabel title;
    //增加线程数，减少线程数按钮
    public JButton add_button;
    public JButton decline_button;
    //增加turn,减少turn

    public JButton add_turn_button;
    public JButton decline_turn_button;

    //线程数显示
    public JTextField pnum_field;
    //turn数显示
    public JTextField tnum_field;
    //运行按钮
    public JButton running_button;
    //可复用：返回按钮
    public JButton return_button;
    public Peterson_General_Option_Page(){

        //面板尺寸设置
        this.setSize(600,400);
        this.setLocation(400,150);

        int buttonWidth = 270;
        int buttonHeight = 50;
        int gap = 25;  // 两个按钮之间的间隔
        int centerX = (this.getWidth() - buttonWidth) / 2;  // 水平居中的x坐标
        int button_num = 0;
        THREAD_NUMS  = 1;

        title = new JLabel("请设置你的进程程数量");
        title.setFont(new Font("隶书", Font.BOLD,25));
        add_button = new JButton("添加进程");
        decline_button = new JButton("减少进程");
        pnum_field = new JTextField("1");

        running_button = new JButton("开始运行");
        return_button = new JButton("返回");

        add_turn_button = new JButton("优先级顺延至下一线程");
        decline_turn_button = new JButton("优先级回溯至上一线程");
        tnum_field = new JTextField("1");




        //添加监听
        add_button.addActionListener(new Add_Listenser());
        decline_button.addActionListener(new Decline_Listenser());
        running_button.addActionListener(new Running_Listenser());
        return_button.addActionListener(new Return_Listenser());



        //关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //使用GridBagLayout布局管理器
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; // 水平填充

        //添加Add按钮
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1; //占用一个单元格
        this.add(add_button, constraints);

        //添加数字显示
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(pnum_field, constraints);
        pnum_field.setHorizontalAlignment(JTextField.CENTER); // 文字居中

        //添加Decline按钮
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(decline_button, constraints);



        //添加运行按钮
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        this.add(running_button, constraints);

        //添加返回按钮
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(return_button, constraints);




    }
    class Add_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                THREAD_NUMS ++;
                pnum_field.setText(String.valueOf(THREAD_NUMS));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_General_Option_Page.this.dispose();
            }
        }
    }
    class Decline_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(THREAD_NUMS>1){
                    THREAD_NUMS --;
                    pnum_field.setText(String.valueOf(THREAD_NUMS));
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_General_Option_Page.this.dispose();
            }
        }
    }
    class Running_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(THREAD_NUMS>0) {
                    int tmp_pnums = THREAD_NUMS;
                    Peterson_General_Option_Page.this.dispose();
                    Peterson_General_Page new_window = new Peterson_General_Page(tmp_pnums);
                    Peterson_Filter filter = new Peterson_Filter(tmp_pnums,new_window);
                    new_window.mymonitor = new Filter_Monitor(filter.flag);
                    new_window.setmonitor();
                    for(int i=1;i<=tmp_pnums;i++){
                        new Thread((new Peterson_General_Thread(i,new_window,filter))).start();
                    }
                    PageMgr.getInstance().special_set(new_window);
                }
                else{
                    JOptionPane.showMessageDialog(null, "进程数不可以为0！");
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_General_Option_Page.this.dispose();
            }
        }
    }

    class Return_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Peterson_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_General_Option_Page.this.dispose();
            }
        }
    }





}
