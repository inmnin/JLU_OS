package Peterson;

import Dekker.Typical.Dekker_Typical_Option_Page;
import Dekker.Typical.Dekker_Typical_Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Peterson_Page {
    public boolean[] flag={false,false};
    public int turn;
    public Peterson_Mypanel0 mypanel0;
    public Peterson_Mypanel1 mypanel1;
    public JDialog main_dialog = null;
    public JTextArea mid_text;
    public JScrollPane mid_scroll;
    public JTextArea right_text;
    public JScrollPane right_scroll;

    public JPanel left_block;
    public JPanel left_mid_block;
    public JButton return_button;
    Peterson_Page(){
        main_dialog = new JDialog();
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
        left_block = new JPanel(new BorderLayout());
        left_block.setSize(main_dialog.getWidth()/3,400);
        main_dialog.add(left_block,BorderLayout.WEST);

        JTextArea  left_title= new JTextArea();
        left_title.setEditable(false);
        left_title.setFont(new Font("隶书", Font.BOLD,25));
        left_title.append("   Peterson算法   ");
        left_title.setBackground(new Color(238,238,238));
        left_block.add(left_title, BorderLayout.NORTH);

        return_button = new JButton("返回");
        return_button.setBounds(10,left_block.getHeight()-35,40,30);
        return_button.addActionListener(new Peterson_Page.Return_Listenser());
        left_block.add(return_button,BorderLayout.SOUTH);

        left_mid_block = new JPanel();
        left_mid_block.setSize(200,200);
        left_mid_block.setLayout(null);
        left_block.add(left_mid_block, BorderLayout.CENTER);

        mypanel0 = new Peterson_Mypanel0();
        mypanel0.setBounds(0,150,150,150);

        mypanel1 = new Peterson_Mypanel1();
        mypanel1.setBounds(0,0,150,150);

        main_dialog.setVisible(true);
        main_dialog.pack();
    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                main_dialog.dispose();
                Peterson_Option_Page new_window = new Peterson_Option_Page();
                new_window.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                main_dialog.dispose();
            }
        }
    }
}
