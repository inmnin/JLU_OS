package Dekker.Typical;

import Dekker.Dekker_Option_Page;
import Dekker.General.Dekker_General_Option_Page;
import Dekker.General.Dekker_General_Page;
import Dekker.General.Dekker_General_Thread;
import Root_Page.Root_Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dekker_Typical_Option_Page extends JFrame {
    public JLabel title = new JLabel("设置进程程优先级");
    //按钮
    public JButton change_turn_button = new JButton("进程优先级切换: 0号进程优先");
    public JButton running_button = new JButton("开始运行");
    public JButton return_button = new JButton("返回");
    //进程优先级
    public JTextField turn_num_text = new JTextField("0");
    int TURN_NUMS = 0;

    public Dekker_Typical_Option_Page(){
        this.setSize(600,500);
        this.setLocation(400,150);
        this.setLayout(null);



        int buttonWidth = 270;
        int buttonHeight = 40;
        int gap = 20;  // 两个按钮之间的间隔
        int centerX = (this.getWidth() - buttonWidth) / 2;  // 水平居中的x坐标
        int button_num = 0;

        //设置标题
        title.setFont(new Font("站酷酷黑", Font.BOLD, 24));
        title.setForeground(Color.BLACK);
        title.setBounds(centerX+31,10,400,20);
        this.add(title);



        //按钮定义与设置
        turn_num_text.setBounds(centerX, (++button_num) * (buttonHeight + gap)+10, buttonWidth, buttonHeight/2+20);
        turn_num_text.setFont(new Font("黑体", Font.BOLD, 25));
        turn_num_text.setEditable(false);
        change_turn_button.setBounds(centerX, (++button_num) * (buttonHeight + gap), buttonWidth, buttonHeight);
        running_button.setBounds(centerX, (++button_num) * (buttonHeight + gap), buttonWidth, buttonHeight);
        return_button.setBounds(centerX, (++button_num) * (buttonHeight + gap), buttonWidth, buttonHeight);



        //为按钮添加监听器
        change_turn_button.addActionListener(new Dekker_Typical_Option_Page.Change_Turn_Listenser());
        running_button.addActionListener(new Dekker_Typical_Option_Page.Running_Listenser());
        return_button.addActionListener(new Dekker_Typical_Option_Page.Return_Listenser());

        this.add(turn_num_text);
        this.add(change_turn_button);
        this.add(running_button);
        this.add(return_button);

    }

    class Change_Turn_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                TURN_NUMS = 1 - TURN_NUMS;
                turn_num_text.setText(String.valueOf(TURN_NUMS));
                change_turn_button.setText("进程优先级切换: "+String.valueOf(TURN_NUMS)+"号进程优先");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Typical_Option_Page.this.dispose();
            }
        }
    }

    class Running_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                    int tmp_tnums = TURN_NUMS;
                    Dekker_Typical_Option_Page.this.dispose();
                    Dekker_Typical_Page new_window = new Dekker_Typical_Page();
                    new_window.turn = tmp_tnums;
                    new Thread(new Dekker_Typical_Thread(0,new_window)).start();
                    new Thread(new Dekker_Typical_Thread(1,new_window)).start();
                }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Typical_Option_Page.this.dispose();
            }
        }
    }

    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                Dekker_Typical_Option_Page.this.dispose();
                Dekker_Option_Page new_window = new Dekker_Option_Page();
                new_window.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Typical_Option_Page.this.dispose();
            }
        }
    }

}
