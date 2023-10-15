package Algorithm_Module.Peterson.Typical;


public class Peterson_Typical_Thread implements Runnable{
    private int id;
    private Peterson_Typical_Page page;

    public Peterson_Typical_Thread(int id, Peterson_Typical_Page page){
        this.id = id;
        this.page = page;
    }
    @Override
    public void run() {
        page.mypanel[0].setcolor(1);
        page.left_mid_block.add(page.mypanel[0]);
        page.mypanel[1].setcolor(1);
        page.left_mid_block.add(page.mypanel[1]);

        page.flag[id] = true;

        //出让优先权
        page.turn = 1-id;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(page.flag[1-id] && page.turn == 1-id){
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

        page.flag[id] = false;

        page.outPut.finishing_output(id);

        if(id == 0){
            page.mypanel[0].setcolor(3);
            page.mypanel[0].repaint();
        }
        else {
            page.mypanel[1].setcolor(3);
            page.mypanel[1].repaint();
        }
    }
}