package Lamport;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lamport_Page{

    //是否进入或者准备进入时，正在取号的状态
    boolean Entering[] = null;

    //领自己的单号
    int Number[] = null;
    int NUM_THREADS;

    public JDialog main_dialog = null;
    //mid件
    public JTextArea mid_text;
    public JScrollPane mid_scroll;
    //right件
    public JPanel left_state_block;
    public JPanel left_mid_block;

    //right件
    public JTextArea right_text;
    public JScrollPane right_scroll;

    //状态图形
    public Lamport_Mypanel mypanel;
    public JButton return_button;

    public Lamport_Page(int n){
        Entering = new boolean[n+1];
        Number = new int[n+1];
        NUM_THREADS = n;
        for(int i=1;i<=n;i++){
            Entering[i] = false;
            Number[i] = 0;
        }

        //主块
        main_dialog = new JDialog();
        main_dialog.setSize(700,400);
        main_dialog.setLocation(400,150);
        main_dialog.setLayout(new BorderLayout());


        //中间状态栏
        mid_text = new JTextArea();
        mid_scroll = new JScrollPane(mid_text);
        mid_text.setEditable(false);
        mid_text.setAutoscrolls(true);
        mid_scroll.setPreferredSize(new Dimension(220, 400));
        main_dialog.add(mid_scroll,BorderLayout.CENTER);

//        //左边状态栏
//        mid_text = new JTextArea();
//        mid_scroll = new JScrollPane(mid_text);
//        mid_text.setEditable(false);
//        mid_text.setAutoscrolls(true);
//        main_dialog.add(mid_scroll,BorderLayout.CENTER);


        //左边图形状态栏
        left_state_block = new JPanel(new BorderLayout());
        main_dialog.add(left_state_block,BorderLayout.WEST);

        JTextArea left_title = new JTextArea();
        left_title.setEditable(false);
        left_title.setFont(new Font("隶书", Font.BOLD,25));
        left_title.append("   Lamport算法   ");
        left_title.setBackground(new Color(238,238,238));
        left_state_block.add(left_title, BorderLayout.NORTH);

        left_mid_block = new JPanel();
        left_mid_block.setSize(200,200 + NUM_THREADS/6 * 50);
        left_mid_block.setLayout(null);
        left_state_block.add(left_mid_block, BorderLayout.CENTER);

        return_button = new JButton("返回");
        return_button.setBounds(10,left_state_block.getHeight()-35,40,30);
        return_button.addActionListener(new Lamport_Page.Return_Listenser());
        left_state_block.add(return_button,BorderLayout.SOUTH);
        
                //右边状态栏
        right_text = new JTextArea();
        right_scroll = new JScrollPane(right_text);
        right_text.setEditable(false);
        right_text.setAutoscrolls(true);
        right_scroll.setPreferredSize(new Dimension(200, 400));
        right_text.append("开始监控进程具体行为：\n");
        main_dialog.add(right_scroll,BorderLayout.EAST);




        mypanel = new Lamport_Mypanel(NUM_THREADS);
        mypanel.setBounds(0,0,200,300 + NUM_THREADS * 50);
        mypanel.setRunning_id(0);
        left_mid_block.add(mypanel);


        main_dialog.pack();
        main_dialog.setVisible(true);

    }

    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                main_dialog.dispose();
                Lamport_Option_Page new_window = new Lamport_Option_Page();
                new_window.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                main_dialog.dispose();
            }
        }
    }
}
