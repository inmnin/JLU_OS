package Algorithm_Module.Dekker.Typical;

import PageMgr.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dekker_Typical_Option_Page extends Double_Thread_Option_Page {


    public Dekker_Typical_Option_Page(){
        //为按钮添加监听器
        running_button.addActionListener(new Dekker_Typical_Option_Page.Running_Listenser());
        return_button.addActionListener(new Dekker_Typical_Option_Page.Return_Listenser());
    }




    class Running_Listenser implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                    int tmp_tnums = TURN_NUMS;
                    Dekker_Typical_Option_Page.this.dispose();
                    Dekker_Typical_Page new_window = new Dekker_Typical_Page();
                    new_window.turn = tmp_tnums;
                    new Thread(new Dekker_Typical_Thread(0,new_window)).start();
                    new Thread(new Dekker_Typical_Thread(1,new_window)).start();
                    PageMgr.getInstance().special_set(new_window);
                }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Typical_Option_Page.this.dispose();
            }
        }
    }

    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Dekker_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Typical_Option_Page.this.dispose();
            }
        }
    }

}
