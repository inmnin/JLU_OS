package Algorithm_Module.Dekker.General;

import Overwatching.Std_Output;
import PageMgr.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dekker_General_Page extends Multi_Thread_Page {

    public volatile boolean[] flag = null;

    //状态图形


    public Dekker_General_Page(int n){
        super(n,"   推广Dekker算法   ");
        outPut = new Std_Output();
        outPut.set_mid_text(mid_text);
        outPut.set_right_text(right_text);
        flag = new boolean[n+1];
        return_button.addActionListener(new Dekker_General_Page.Return_Listenser());
    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Dekker_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_General_Page.this.dispose();
            }
        }
    }


}
