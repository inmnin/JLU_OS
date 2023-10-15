package Algorithm_Module.Lamport;



import Overwatching.Lamport_Output;
import Overwatching.Std_Output;
import PageMgr.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lamport_Page extends Multi_Thread_Page {

    //是否进入或者准备进入时，正在取号的状态
    boolean Entering[] = null;

    //领自己的单号
    int Number[] = null;
    Lamport_Output self_output;

    public Lamport_Page(int n){
        super(n,"   Lamport算法   ");

        outPut = new Std_Output();
        outPut.set_mid_text(mid_text);
        outPut.set_right_text(right_text);
        self_output = new Lamport_Output();
        self_output.set_mid_text(mid_text);
        self_output.set_right_text(right_text);

        Entering = new boolean[n+1];
        Number = new int[n+1];
        for(int i=1;i<=n;i++){
            Entering[i] = false;
            Number[i] = 0;
        }

    return_button.addActionListener(new Lamport_Page.Return_Listenser());
    }

    class Return_Listenser implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PageMgr.getInstance().setActivepage(PageType.Root_Page);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "错误输入！");
                Lamport_Page.this.dispose();
            }
        }
    }
}
