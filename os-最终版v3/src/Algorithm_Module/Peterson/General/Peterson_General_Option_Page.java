package Algorithm_Module.Peterson.General;


import PageMgr.*;
import Visualization.Filter_Monitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Peterson_General_Option_Page extends Multi_Thread_Option_noturnPage {

    public Peterson_General_Option_Page(){


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
                Peterson_General_Option_Page.this.dispose();
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
                Peterson_General_Option_Page.this.dispose();
            }
        }
    }
    class Running_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(THREAD_NUMS>0) {
                    int tmp_pnums = THREAD_NUMS;
                    Peterson_General_Option_Page.this.dispose();
                    Peterson_General_Page new_window = new Peterson_General_Page(tmp_pnums);
                    Peterson_Filter filter = new Peterson_Filter(tmp_pnums,new_window);
                    new_window.mymonitor = new Filter_Monitor(filter.flag);
                    new_window.setmonitor();
                    for(int i=1;i<=tmp_pnums;i++){
                        new Thread((new Peterson_General_Thread(i,new_window,filter))).start();
                    }
                    PageMgr.getInstance().special_set(new_window);
                }
                else{
                    JOptionPane.showMessageDialog(null, "进程数不可以为0！");
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_General_Option_Page.this.dispose();
            }
        }
    }

    class Return_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Peterson_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_General_Option_Page.this.dispose();
            }
        }
    }





}
