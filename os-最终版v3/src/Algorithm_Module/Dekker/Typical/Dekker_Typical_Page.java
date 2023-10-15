package Algorithm_Module.Dekker.Typical;

import Overwatching.Std_Output;
import PageMgr.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dekker_Typical_Page extends Double_Thread_Page{
    public int[] flag= {0,0};
    public Dekker_Typical_Page(){
        super();
        outPut = new Std_Output();
        outPut.set_right_text(right_text);
        outPut.set_mid_text(mid_text);
        left_title.append("   经典的Dekker算法   ");
        return_button.addActionListener(new Dekker_Typical_Page.Return_Listenser());
    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Dekker_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Dekker_Typical_Page.this.dispose();
            }
        }
    }



}