package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Dekker_input extends JFrame{
    public JFrame J = new JFrame("选择开始的进程");
    public JTextField first_pro = new JTextField();

    public JButton num =  new JButton("第一个运行的线程号");
    public JButton ok = new JButton("开始！");
    private Dekker_input.Listener_Button decision;
    private Dekker_input.Listener_Begin begin;
    public int starting_num;
    public JFrame F;
    public Dekker_input(JFrame f){
        F = f;
        J.setSize(600,400);
        J.setLocation(900,200);
//        J.setLayout(new BorderLayout());

        int buttonWidth = 270;
        int buttonHeight = 60;
        int gap = 10;  // 两个按钮之间的间隔
        int centerX = (J.getWidth() - buttonWidth) / 2;  // 水平居中的x坐标


        ok.setBounds(centerX, 50 + 3 * (buttonHeight + gap), buttonWidth, buttonHeight);

        num.setBounds(centerX,50 + 1 * (buttonHeight + gap),buttonWidth,buttonHeight);
        num.setVisible(true);
        num.setText("请输入由哪个进程为第一个运行进程");



        starting_num = -1;

        decision = new Dekker_input.Listener_Button();
        begin = new Dekker_input.Listener_Begin();
        num.addActionListener(decision);
        ok.addActionListener(begin);

        J.add(ok);
        J.add(num);

        J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        J.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // 当 dekker_input 被关闭后，创建并显示 Dekker_Resource
                if(starting_num!=-1){
                Dekker_Resource resource = new Dekker_Resource(F);
                System.out.print("num = ");
                System.out.print(starting_num);
                new Thread(new Dekker(starting_num,resource)).start();
                new Thread(new Dekker(1-starting_num,resource)).start();
            }
        }
    });

        first_pro.setBounds(centerX,50 + 2 * (buttonHeight + gap),buttonWidth,buttonHeight);
        first_pro.setEditable(true);
        first_pro.setVisible(false);
//        first_pro.setText("请输入由哪个进程为第一个运行进程");
        first_pro.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                first_pro.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {

            }
        });
        J.add(first_pro);




    }
    class Listener_Begin implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (!first_pro.getText().toString().equals(""))
                    starting_num = Integer.valueOf(first_pro.getText().toString());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                J.dispose();
            }
        J.dispose();
        }
    }
    class Listener_Button implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            first_pro.setEditable(true);
            first_pro.setVisible(true);
        }
    }


}
