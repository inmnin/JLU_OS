package Algorithm_Module.Peterson;

import PageMgr.*;

import javax.swing.*;
import java.awt.event.*;


public class Peterson_Option_Page extends Page {

    //运行经典Peterson算法
    public JButton typical_begin_button = new JButton("经典Peterson算法");

    //运行推广的Peterson算法
    public JButton general_begin_button = new JButton("广义的Peterson算法");

    //返回
    public JButton return_button = new JButton("返回");
    ;


    public Peterson_Option_Page() {

        //面板尺寸设置
        this.setSize(600, 400);
        this.setLocation(400, 150);
        this.setLayout(null);

        int buttonWidth = 270;
        int buttonHeight = 40;
        int gap = 20;  // 两个按钮之间的间隔
        int centerX = (this.getWidth() - buttonWidth) / 2;  // 水平居中的x坐标
        int button_num = 0;


        //按钮定义与设置


        general_begin_button.setBounds(centerX, (++button_num) * (buttonHeight + gap), buttonWidth, buttonHeight);
        typical_begin_button.setBounds(centerX, (++button_num) * (buttonHeight + gap), buttonWidth, buttonHeight);
        return_button.setBounds(centerX, (++button_num) * (buttonHeight + gap), buttonWidth, buttonHeight);



        //为按钮添加监听器
        typical_begin_button.addActionListener(new Typical_Begin_Listenser());
        general_begin_button.addActionListener(new General_Begin_Listenser());
        return_button.addActionListener(new Return_Listenser());

        //添加按钮与输入框
        this.add(typical_begin_button);
        this.add(general_begin_button);
        this.add(return_button);


    }

    class Typical_Begin_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Peterson_Typical_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_Option_Page.this.dispose();
            }
        }
    }

    class General_Begin_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Peterson_General_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_Option_Page.this.dispose();
            }


        }
    }


    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Root_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_Option_Page.this.dispose();
            }
        }
    }
}

