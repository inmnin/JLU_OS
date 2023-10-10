package Eisenberg;

import Dekker.General.Dekker_General_Option_Page;
import Dekker.General.Dekker_General_Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class Eisenberg_Page extends JDialog {
    int flags[] = null;
    int NUM_THREADS;
    int IDLE = 0;
    int WAITING = 1;
    int ACTIVE = 2;
    int turn;

    public JTextArea mid_text;
    public JScrollPane mid_scroll;
    public JPanel left_block;
    public JPanel left_mid_block;
    public JTextArea right_text;
    public JScrollPane right_scroll;
    public JButton return_button;

    public Eisenberg_Mypanel mypanel;

    public Eisenberg_Page(int n){
        flags = new int[n+1];
        for(int i=1;i<=n;i++)
            flags[i] = IDLE;
        NUM_THREADS = n;
        Random random = new Random();
        turn = random.nextInt(n);

        this.setSize(700,400);
        this.setLocation(400,150);
        this.setLayout(new BorderLayout());

        mid_text = new JTextArea();
        mid_scroll = new JScrollPane(mid_text);
        mid_text.setEditable(false);
        mid_text.setAutoscrolls(true);
        mid_scroll.setPreferredSize(new Dimension(200, 400));
        this.add(mid_scroll,BorderLayout.CENTER);

        left_block = new JPanel(new BorderLayout());
        left_block.setSize(200,400);
        this.add(left_block,BorderLayout.WEST);

        JTextArea title = new JTextArea();
        title.setEditable(false);
        title.setFont(new Font("隶书", Font.BOLD,25));
        title.append("   Eisenberg算法   ");
        title.setBackground(new Color(238,238,238));
        left_block.add(title, BorderLayout.NORTH);

        left_mid_block = new JPanel();
        left_mid_block.setSize(200,200);
        left_mid_block.setLayout(null);
        left_block.add(left_mid_block, BorderLayout.CENTER);


        return_button = new JButton("返回");
        return_button.setBounds(10,left_block.getHeight()-35,40,30);
        return_button.addActionListener(new Eisenberg_Page.Return_Listenser());
        left_block.add(return_button,BorderLayout.SOUTH);


        right_text = new JTextArea();
        right_scroll = new JScrollPane(right_text);
        right_text.setEditable(false);
        right_text.setAutoscrolls(true);
        right_scroll.setPreferredSize(new Dimension(200, 400));
        right_text.append("开始监控进程具体行为：\n");
        this.add(right_scroll,BorderLayout.EAST);


        mypanel = new Eisenberg_Mypanel(NUM_THREADS);
        mypanel.setBounds(0,0,200, 300+NUM_THREADS * 5);
        mypanel.setRunning_id(0);
        left_mid_block.add(mypanel);


        this.pack();
        this.setVisible(true);

    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                Eisenberg_Page.this.dispose();
                Eisenberg_Option_Page new_window = new Eisenberg_Option_Page();
                new_window.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Eisenberg_Page.this.dispose();
            }
        }
    }

}
