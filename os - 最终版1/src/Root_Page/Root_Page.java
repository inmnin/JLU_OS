package Root_Page;

import Lamport.*;
import Dekker.*;
import Eisenberg.*;
import Peterson.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Root_Page {
    private Listener_Button decision;
    private Listener_Begin begin;
    private int NUM_THREADS;
    private int sign;
    public JFrame main_frame = new JFrame("并发进程控制");
    private JButton running_button = new JButton("开始运行算法");
    private JButton dekker_button = new JButton("Dekker算法");
    private JButton peterson_button = new JButton("Peterson算法");
    private JButton lamport_button = new JButton("Lamport算法");
    private JButton eisenberg_button = new JButton("Eisenberg/Mcgouire算法");


    public Root_Page(){
        main_frame.setSize(500,500);
        main_frame.setLocation(400,150);
        main_frame.setLayout(null);


        //保持a1-ok居中
        int buttonWidth = 270;
        int buttonHeight = 60;
        int gap = 10;  // 两个按钮之间的间隔

        int centerX = (main_frame.getWidth() - buttonWidth) / 2;  // 水平居中的x坐标
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

        main_frame.add(dekker_button);
        main_frame.add(peterson_button);
        main_frame.add(lamport_button);
        main_frame.add(eisenberg_button);
        main_frame.add(running_button);
        main_frame.add(mp);
        main_frame.setVisible(true);


        JLabel ch1 = new JLabel("基于软件互斥算法的临界区进程互斥的模拟实现");
        Font newFont = new Font("站酷酷黑", Font.BOLD, 18);
        ch1.setFont(newFont);

        ch1.setForeground(Color.BLACK);
        ch1.setBounds(50,10,400,30);
        main_frame.add(ch1);





    }

    class Listener_Button implements ActionListener{              //根据按钮，用sign来保存要调用哪个算法函数
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource().equals(dekker_button)){
                sign = 1;
            }
            if(actionEvent.getSource().equals(peterson_button)){
                sign = 2;
            }
            if(actionEvent.getSource().equals(lamport_button)){
                sign = 3;
            }
            if(actionEvent.getSource().equals(eisenberg_button)){
                sign = 4;
            }
        }
    }

    class Listener_Begin implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            try{
                switch (sign){
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
        main_frame.dispose();
        Dekker_Option_Page dekker_option_page= new Dekker_Option_Page();
        dekker_option_page.setVisible(true);
    }
    private void Peterson(){
        main_frame.dispose();
        Peterson_Option_Page peterson_option_page = new Peterson_Option_Page();
        peterson_option_page.setVisible(true);
    }
    private void Lamport(){
        main_frame.dispose();
        Lamport_Option_Page lamport_option_page = new Lamport_Option_Page();
        lamport_option_page.setVisible(true);
    }
    private void Eisenberg(){
        main_frame.dispose();
        Eisenberg_Option_Page eisenberg_option_page = new Eisenberg_Option_Page();
        eisenberg_option_page.setVisible(true);
    }


}


