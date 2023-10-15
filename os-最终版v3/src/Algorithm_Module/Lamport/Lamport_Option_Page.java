package Algorithm_Module.Lamport;


import PageMgr.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lamport_Option_Page extends Multi_Thread_Option_noturnPage {

    public Lamport_Option_Page() {
        //面板尺寸设置

        //添加监听
        add_button.addActionListener(new Add_Listenser());
        decline_button.addActionListener(new Decline_Listenser());
        running_button.addActionListener(new Running_Listenser());
        return_button.addActionListener(new Return_Listenser());
    }
    class Add_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                THREAD_NUMS ++;
                pnum_field.setText(String.valueOf(THREAD_NUMS));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Lamport_Option_Page.this.dispose();
            }
        }
    }
    class Decline_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(THREAD_NUMS>1){
                    THREAD_NUMS --;
                    pnum_field.setText(String.valueOf(THREAD_NUMS));
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Lamport_Option_Page.this.dispose();
            }
        }
    }
    class Running_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(THREAD_NUMS>0) {
                    int tmp_pnums = THREAD_NUMS;
                    Lamport_Option_Page.this.dispose();
                    Lamport_Page new_window = new Lamport_Page(tmp_pnums);
                    for(int i=1;i<=tmp_pnums;i++){
                        new Thread((new Lamport_Thread(i,new_window))).start();
                    }
                    PageMgr.getInstance().special_set(new_window);
                }
                else{
                    JOptionPane.showMessageDialog(null, "进程数不可以为0！");
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Lamport_Option_Page.this.dispose();
            }
        }
    }

    class Return_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Root_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Lamport_Option_Page.this.dispose();
            }
        }
    }





}
