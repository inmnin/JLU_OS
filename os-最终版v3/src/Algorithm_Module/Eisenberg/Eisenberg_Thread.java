package Algorithm_Module.Eisenberg;

public class Eisenberg_Thread implements Runnable {
    int id;
    Eisenberg_Page page;

    Eisenberg_Thread(int id, Eisenberg_Page page) {
        this.id = id;
        this.page = page;
    }

    @Override
    public void run() {
        int index;
        do {
            page.flags[id] = page.WAITING;
            index = page.turn;
            while (index != id) {
                if (page.flags[index] != page.IDLE)
                    index = page.turn;
                else {
                    index = (index % page.NUM_THREADS) + 1; // Mod adjustment
                }
                page.outPut.waiting_output(id);
//                page.mid_text.append("进程" + id + "试图进入临界区被拒绝\n");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            page.flags[id] = page.ACTIVE;
            index = 1; // Start from 1
            while (page.flags[index] != page.ACTIVE){
                index = (index % page.NUM_THREADS) + 1; // Mod adjustment
            }

        } while (!(index == id && (page.turn == id || page.flags[page.turn] == page.IDLE)));
        page.turn = id;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //准入输出
        page.outPut.ready_output(id);

        page.mypanel.setRunning_id(id);
        page.mypanel.repaint();

        //正在访问
        page.outPut.visiting_output(id);

        index = (page.turn % page.NUM_THREADS) + 1; // Mod adjustment
        while (page.flags[index] == page.IDLE) {
            index = (index % page.NUM_THREADS) + 1; // Mod adjustment
        }
        page.turn = index;

        //结束输出
        page.outPut.finishing_output(id);

        page.mypanel.setRunning_id(id);
        page.mypanel.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.flags[id] = page.IDLE;
    }
}
