package Dekker.General;

import Dekker.Dekker_Option_Page;
import PageMgr.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dekker_General_Option_Page extends Page {

    int THREAD_NUMS;
    int TURN_NUMS;
    public  JLabel title;
    //增加线程数，减少线程数按钮
    public JButton add_button;
    public Add_Listenser add_listenser;
    public JButton decline_button;
    public Decline_Listenser decline_listenser;

    //增加turn,减少turn

    public JButton add_turn_button;
    public Add_Turn_Listenser add_turn_listenser;
    public JButton decline_turn_button;
    public Decline_Turn_Listenser decline_turn_listenser;


    //线程数显示
    public JTextField pnum_field;
    //turn数显示
    public JTextField tnum_field;
    //运行按钮
    public JButton running_button;
    public Running_Listenser running_listenser;
    //可复用：返回按钮
    public JButton return_button;
    public Return_Listenser return_listenser;
    public Dekker_General_Option_Page(){
        //面板尺寸设置
        this.setSize(600,400);
        this.setLocation(400,150);

        int buttonWidth = 270;
        int buttonHeight = 50;
        int gap = 25;  // 两个按钮之间的间隔
        int centerX = (this.getWidth() - buttonWidth) / 2;  // 水平居中的x坐标
        int button_num = 0;
        TURN_NUMS = 1;
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
        Add_Listenser add_listenser = new Add_Listenser();
        Decline_Listenser decline_listenser = new Decline_Listenser();
        Running_Listenser running_listenser = new Running_Listenser();
        Return_Listenser return_listenser = new Return_Listenser();
        add_button.addActionListener(add_listenser);
        decline_button.addActionListener(decline_listenser);
        running_button.addActionListener(running_listenser);
        return_button.addActionListener(return_listenser);

        add_turn_button.addActionListener(new Add_Turn_Listenser());
        decline_turn_button.addActionListener(new Decline_Turn_Listenser());


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

        //添加Add_turn按钮
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1; //占用一个单元格
        this.add(add_turn_button, constraints);

        //添加数字显示
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        this.add(tnum_field, constraints);
        tnum_field.setHorizontalAlignment(JTextField.CENTER); // 文字居中

        //添加Decline_turn按钮
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        this.add(decline_turn_button, constraints);


        //添加运行按钮
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(running_button, constraints);

        //添加返回按钮
        constraints.gridx = 1;
        constraints.gridy = 3;
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
                Dekker_General_Option_Page.this.dispose();
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
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }
    class Running_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(THREAD_NUMS>0) {
                    int tmp_pnums = THREAD_NUMS;
                    Dekker_General_Option_Page.this.dispose();
                    Dekker_General_Page new_window = new Dekker_General_Page(tmp_pnums);
                    new_window.setVisible(true);
                    if(TURN_NUMS<1)
                        TURN_NUMS = 1;
                    new_window.turn = TURN_NUMS;
                    for(int i=1;i<=tmp_pnums;i++){
                        new Thread((new Dekker_General_Thread(i,new_window))).start();
                    }
                    PageMgr.getInstance().special_set(new_window);
                }
                else{
                    JOptionPane.showMessageDialog(null, "进程数不可以为0！");
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }

    class Return_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Dekker_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }

    class Add_Turn_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                TURN_NUMS = (TURN_NUMS)%THREAD_NUMS+1;
                tnum_field.setText(String.valueOf(TURN_NUMS));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }
    class Decline_Turn_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(TURN_NUMS>1){
                    TURN_NUMS --;
                    tnum_field.setText(String.valueOf(TURN_NUMS));
                }
                else {TURN_NUMS = THREAD_NUMS;
                      tnum_field.setText(String.valueOf(TURN_NUMS));
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }



}
