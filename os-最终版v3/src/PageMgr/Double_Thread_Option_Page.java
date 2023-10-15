package PageMgr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Double_Thread_Option_Page extends Page{
    public JLabel title = new JLabel("设置进程程优先级");
    //按钮
    public JButton change_turn_button = new JButton("进程优先级切换: 0号进程优先");
    public JButton running_button = new JButton("开始运行");
    public JButton return_button = new JButton("返回");
    //进程优先级
    public JTextField turn_num_text = new JTextField("0");
    public int TURN_NUMS = 0;
    public Double_Thread_Option_Page(){
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

        this.add(turn_num_text);
        this.add(change_turn_button);
        this.add(running_button);
        this.add(return_button);

        change_turn_button.addActionListener(new Double_Thread_Option_Page.Change_Turn_Listenser());
    }
    class Change_Turn_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                TURN_NUMS = 1 - TURN_NUMS;
                turn_num_text.setText(String.valueOf(TURN_NUMS));
                change_turn_button.setText("进程优先级切换: "+String.valueOf(TURN_NUMS)+"号进程优先");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
            }
        }
    }
}
