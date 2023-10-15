package Root_Page;

import PageMgr.Page;
import PageMgr.PageMgr;
import PageMgr.PageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Root_Page extends Page {
    private Listener_Button decision;
    private Listener_Begin begin;
    private int NUM_THREADS;
    private int option;

    private JButton running_button = new JButton("开始运行算法");
    private JButton dekker_button = new JButton("Dekker算法");
    private JButton peterson_button = new JButton("Peterson算法");
    private JButton lamport_button = new JButton("Lamport算法");
    private JButton eisenberg_button = new JButton("Eisenberg/Mcgouire算法");


    public Root_Page(){
        this.setTitle("并发进程控制");
        this.setSize(500,500);
        this.setLocation(400,150);
        this.setLayout(null);


        //保持a1-ok居中
        int buttonWidth = 270;
        int buttonHeight = 60;
        int gap = 10;  // 两个按钮之间的间隔

        int centerX = (this.getWidth() - buttonWidth) / 2;  // 水平居中的x坐标
        dekker_button.setBounds(centerX, 50, buttonWidth, buttonHeight);
        peterson_button.setBounds(centerX, 50 + buttonHeight + gap, buttonWidth, buttonHeight);
        lamport_button.setBounds(centerX, 50 + 2 * (buttonHeight + gap), buttonWidth, buttonHeight);
        eisenberg_button.setBounds(centerX, 50 + 3 * (buttonHeight + gap), buttonWidth, buttonHeight);
        running_button.setBounds(centerX, 50 + 4 * (buttonHeight + gap)+20, buttonWidth, 40);

        Panel mp = new Panel();
        mp.setBounds(centerX,50+4 * (buttonHeight + gap)-2,270,1);
        mp.setBackground(Color.BLACK);

        decision = new Listener_Button();
        begin = new Listener_Begin();
        dekker_button.addActionListener(decision);
        peterson_button.addActionListener(decision);
        lamport_button.addActionListener(decision);
        eisenberg_button.addActionListener(decision);
        running_button.addActionListener(begin);

        this.add(dekker_button);
        this.add(peterson_button);
        this.add(lamport_button);
        this.add(eisenberg_button);
        this.add(running_button);
        this.add(mp);
        this.setVisible(true);


        JLabel ch1 = new JLabel("基于软件互斥算法的临界区进程互斥的模拟实现");
        Font newFont = new Font("站酷酷黑", Font.BOLD, 18);
        ch1.setFont(newFont);

        ch1.setForeground(Color.BLACK);
        ch1.setBounds(50,10,400,30);
        this.add(ch1);





    }

    class Listener_Button implements ActionListener{              //根据按钮，用sign来保存要调用哪个算法函数
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource().equals(dekker_button)){
                option = 1;
            }
            if(actionEvent.getSource().equals(peterson_button)){
                option = 2;
            }
            if(actionEvent.getSource().equals(lamport_button)){
                option = 3;
            }
            if(actionEvent.getSource().equals(eisenberg_button)){
                option = 4;
            }
        }
    }

    class Listener_Begin implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            try{
                switch (option){
                    case 1:
                        Dekker();
                        break;
                    case 2:
                        Peterson();
                        break;
                    case 3:
                        Lamport();
                        break;
                    case 4:
                        Eisenberg();
                        break;
                }
              }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"错误输入！");
            }


        }
    }

    private void Dekker(){
        PageMgr.getInstance().setActivepage(PageType.Dekker_Option_Page);
    }
    private void Peterson(){
        PageMgr.getInstance().setActivepage(PageType.Peterson_Option_Page);
    }
    private void Lamport(){
        PageMgr.getInstance().setActivepage(PageType.Lamport_Option_Page);
    }
    private void Eisenberg(){
        PageMgr.getInstance().setActivepage(PageType.Eisenberg_Option_Page);
    }


}


