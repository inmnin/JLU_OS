package Algorithm_Module.Peterson.Typical;

import Overwatching.Std_Output;
import PageMgr.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Peterson_Typical_Page extends Double_Thread_Page {
    public boolean[] flag={false,false};
    public Peterson_Typical_Page(){
        super();
        outPut = new Std_Output();
        outPut.set_mid_text(mid_text);
        outPut.set_right_text(right_text);

        left_title.append("   经典Peterson算法   ");
        return_button.addActionListener(new Peterson_Typical_Page.Return_Listenser());
    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Peterson_Typical_Option_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Peterson_Typical_Page.this.dispose();
            }
        }
    }
}
