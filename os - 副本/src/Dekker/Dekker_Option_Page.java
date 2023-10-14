package Dekker;

import Dekker.General.*;
import Dekker.Typical.*;
import Root_Page.Root_Page;

import javax.swing.*;
import java.awt.event.*;


public class Dekker_Option_Page extends JFrame {

    //运行经典dekker算法
    public JButton typical_begin_button = new JButton("经典dekker算法");

    //运行推广的dekker算法
    public JButton general_begin_button = new JButton("广义的dekker算法");

    //返回
    public JButton return_button = new JButton("返回");
    ;


    public Dekker_Option_Page() {

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
                Dekker_Option_Page.this.dispose();
                Dekker_Typical_Option_Page new_window = new Dekker_Typical_Option_Page();
                new_window.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Option_Page.this.dispose();
            }
        }
    }

    class General_Begin_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                Dekker_Option_Page.this.dispose();
                Dekker_General_Option_Page new_window = new Dekker_General_Option_Page();
                new_window.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Option_Page.this.dispose();
            }


        }
    }


    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                Dekker_Option_Page.this.dispose();
                Root_Page new_window = new Root_Page();
                new_window.main_frame.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Option_Page.this.dispose();
            }
        }
    }
}

