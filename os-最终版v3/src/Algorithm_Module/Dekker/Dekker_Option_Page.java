package Algorithm_Module.Dekker;

import PageMgr.*;

import javax.swing.*;
import java.awt.event.*;


public class Dekker_Option_Page extends Page {

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
        typical_begin_button.addActionListener(new Typical_Running_Listenser());
        general_begin_button.addActionListener(new General_Running_Listenser());
        return_button.addActionListener(new Return_Listenser());

        //添加按钮与输入框
        this.add(typical_begin_button);
        this.add(general_begin_button);
        this.add(return_button);


    }

    class Typical_Running_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Dekker_Typical_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Option_Page.this.dispose();
            }
        }
    }

    class General_Running_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Dekker_General_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Option_Page.this.dispose();
            }


        }
    }


    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Root_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Option_Page.this.dispose();
            }
        }
    }
}

