package PageMgr;

import javax.swing.*;
import java.awt.*;

public class Multi_Thread_Option_noturnPage extends Page {
    protected int THREAD_NUMS;
    public JLabel title;
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
    public Multi_Thread_Option_noturnPage(){

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
}
