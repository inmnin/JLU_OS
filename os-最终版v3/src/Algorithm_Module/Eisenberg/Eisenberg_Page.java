package Algorithm_Module.Eisenberg;

import Overwatching.Std_Output;
import PageMgr.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eisenberg_Page extends Multi_Thread_Page {
    int flags[] = null;

    int IDLE = 0;
    int WAITING = 1;
    int ACTIVE = 2;




    public Eisenberg_Page(int n){
        super(n,"   Eisenberg算法   ");

        outPut = new Std_Output();
        outPut.set_right_text(right_text);
        outPut.set_mid_text(mid_text);

        flags = new int[n+1];
        for(int i=1;i<=n;i++)
            flags[i] = IDLE;
        return_button.addActionListener(new Eisenberg_Page.Return_Listenser());

    }
    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Root_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Eisenberg_Page.this.dispose();
            }
        }
    }

}
