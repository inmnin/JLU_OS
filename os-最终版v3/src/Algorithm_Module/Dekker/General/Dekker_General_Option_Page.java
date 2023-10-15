package Algorithm_Module.Dekker.General;

import PageMgr.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dekker_General_Option_Page extends Multi_Thread_Option_turnPage {




    public Dekker_General_Option_Page(){

        //添加监听
        add_button.addActionListener(new Add_Listenser());
        decline_button.addActionListener(new Decline_Listenser());
        running_button.addActionListener(new Running_Listenser());
        return_button.addActionListener(new Return_Listenser());
        add_turn_button.addActionListener(new Add_Turn_Listenser());
        decline_turn_button.addActionListener(new Decline_Turn_Listenser());
    }
    class Add_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                THREAD_NUMS ++;
                pnum_field.setText(String.valueOf(THREAD_NUMS));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
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
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }
    class Running_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(THREAD_NUMS>0) {
                    int tmp_pnums = THREAD_NUMS;
                    Dekker_General_Option_Page.this.dispose();
                    Dekker_General_Page new_window = new Dekker_General_Page(tmp_pnums);
                    new_window.setVisible(true);
                    if(TURN_NUMS<1)
                        TURN_NUMS = 1;
                    new_window.turn = TURN_NUMS;
                    for(int i=1;i<=tmp_pnums;i++){
                        new Thread((new Dekker_General_Thread(i,new_window))).start();
                    }
                    PageMgr.getInstance().special_set(new_window);
                }
                else{
                    JOptionPane.showMessageDialog(null, "进程数不可以为0！");
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }

    class Return_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Dekker_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }

    class Add_Turn_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                TURN_NUMS = (TURN_NUMS)%THREAD_NUMS+1;
                tnum_field.setText(String.valueOf(TURN_NUMS));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }
    class Decline_Turn_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(TURN_NUMS>1){
                    TURN_NUMS --;
                    tnum_field.setText(String.valueOf(TURN_NUMS));
                }
                else {TURN_NUMS = THREAD_NUMS;
                      tnum_field.setText(String.valueOf(TURN_NUMS));
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Option_Page.this.dispose();
            }
        }
    }



}
