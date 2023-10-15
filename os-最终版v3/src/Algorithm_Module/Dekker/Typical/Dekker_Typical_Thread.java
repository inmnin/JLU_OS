package Algorithm_Module.Dekker.Typical;


public class Dekker_Typical_Thread implements Runnable {
    private int id;
    private Dekker_Typical_Page page;

    public Dekker_Typical_Thread(int id, Dekker_Typical_Page page){
        this.id = id;
        this.page = page;
    }
    @Override
    public void run() {
        //我想进入
        page.flag[id] = 1;

        //初始化红绿灯状态
        page.mypanel[0].setcolor(1);
        page.left_mid_block.add(page.mypanel[0]);
        page.mypanel[1].setcolor(1);
        page.left_mid_block.add(page.mypanel[1]);

        //小睡一会儿
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //循环谦让
        while (page.flag[1-id] != 0){
            //谦让
            page.flag[id] = 0;
            //等对面进入临界区
            while(page.turn != id){
                page.outPut.waiting_output(id);
                if(id == 0){
                    page.mypanel[0].setcolor(1);
                    page.mypanel[0].repaint();
                }
                else {
                    page.mypanel[1].setcolor(1);
                    page.mypanel[1].repaint();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            page.flag[id] = 1;
        }

        //进入临界区
        page.outPut.ready_output(id);

        if(id == 0){
            page.mypanel[0].setcolor(2);
            page.mypanel[0].repaint();
        }
        else {
            page.mypanel[1].setcolor(2);
            page.mypanel[1].repaint();
        }

        page.outPut.visiting_output(id);

        //退出临界区后该出让优先级了
        page.turn = 1-id;

        page.outPut.finishing_output(id);
        if(id == 0){
            page.mypanel[0].setcolor(3);
            page.mypanel[0].repaint();
        }
        else {
            page.mypanel[1].setcolor(3);
            page.mypanel[1].repaint();
        }
        //抹去访问申请标记
        page.flag[id] = 0;
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
        }
    }

}


