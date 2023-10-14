package Dekker.Typical;


import Dekker.General.Dekker_General_Option_Page;
import Dekker.General.Dekker_General_Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Dekker_Typical_Page {
    public int[] flag= {0,0};
    volatile boolean []finished={false,false};
    public int turn;
    public Dekker_Typical_Mypanel0 mypanel0;
    public Dekker_Typical_Mypanel1 mypanel1;
    public JDialog main_dialog = null;

    public JTextArea mid_text;
    public JScrollPane mid_scroll;
    public JTextArea right_text;
    public JScrollPane right_scroll;
    public JPanel left_panel;
    public JPanel left_mid_panel;
    public JButton return_button;

    public Dekker_Typical_Page(){
        Random ra =new Random();
        turn =( ra.nextInt(10)+1) % 2;

        main_dialog = new JDialog(); //?
        main_dialog.setSize(600,400);
        main_dialog.setLocation(400,150);
        main_dialog.setLayout(new BorderLayout());

        //面板中间显示谁正在运行的文本框
        mid_text = new JTextArea();
        mid_scroll = new JScrollPane(mid_text);
        mid_text.setEditable(false);
        mid_text.setAutoscrolls(true);
        main_dialog.add(mid_scroll,BorderLayout.CENTER);
        mid_scroll.setPreferredSize(new Dimension(main_dialog.getWidth()/3, 400));

        //右边的线程运行信息输出框
        right_text = new JTextArea();
        right_scroll = new JScrollPane(right_text);
        right_text.setEditable(false);
        right_text.setAutoscrolls(true);
        main_dialog.add(right_scroll,BorderLayout.EAST);
        right_scroll.setPreferredSize(new Dimension(main_dialog.getWidth()/3, 400)); // 例如，宽度为150px

        //左边面板及其相关的控件
        left_panel = new JPanel(new BorderLayout());
        left_panel.setSize(main_dialog.getWidth()/3,400);
        main_dialog.add(left_panel,BorderLayout.WEST);

        JTextArea  left_title= new JTextArea();
        left_title.setEditable(false);
        left_title.setFont(new Font("隶书", Font.BOLD,25));
        left_title.append("   经典的Dekker算法   ");
        left_title.setBackground(new Color(238,238,238));
        left_panel.add(left_title, BorderLayout.NORTH);

        return_button = new JButton("返回");
        return_button.setBounds(10,left_panel.getHeight()-35,40,30);
        return_button.addActionListener(new Dekker_Typical_Page.Return_Listenser());
        left_panel.add(return_button,BorderLayout.SOUTH);

        //左边小组件
        left_mid_panel = new JPanel();
        left_mid_panel.setSize(200,200);
        left_mid_panel.setLayout(null);
        left_panel.add(left_mid_panel, BorderLayout.CENTER);

        mypanel0 = new Dekker_Typical_Mypanel0();
        mypanel0.setBounds(0,150,150,150);

        mypanel1 = new Dekker_Typical_Mypanel1();
        mypanel1.setBounds(0,0,150,150);

        main_dialog.setVisible(true);
        main_dialog.pack();
    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                main_dialog.dispose();
                Dekker_Typical_Option_Page new_window = new Dekker_Typical_Option_Page();
                new_window.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                main_dialog.dispose();
            }
        }
    }



}