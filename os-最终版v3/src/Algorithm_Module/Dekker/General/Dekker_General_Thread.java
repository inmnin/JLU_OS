package Algorithm_Module.Dekker.General;

public class Dekker_General_Thread implements Runnable{
    int id;
    Dekker_General_Page page;

    public Dekker_General_Thread(int id, Dekker_General_Page page)  {
        this.id = id;
        this.page = page;
    }

    @Override
    public void run() {

        // 我想进入
        synchronized (page){
            page.flag[id] = true;
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(true) {
                synchronized (page) {
                    boolean can_in = true;
                    for (int i = 1; i <= page.NUM_THREADS; i++) {
                        if (i != id) {
                            if (page.flag[i]) {
                                page.flag[id] = false;
                                can_in = false;
                            }
                        }
                    }
                    if(can_in == true) break;
                }

            int maxTries = 3;  // 设置最大尝试次数为5，这意味着线程最多会等待10秒
            int timeoutCounter = 0;
            while(page.turn != id && timeoutCounter<maxTries){
                page.outPut.waiting_output(id);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeoutCounter++;
                }
            synchronized (page)
            {page.flag[id] = true;}
        }


            page.outPut.ready_output(id);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            page.mypanel.setRunning_id(id);
            page.mypanel.repaint();

            page.outPut.visiting_output(id);
            page.outPut.finishing_output(id);

            //绘制绿色图标
            page.mypanel.setRunning_id(id);
            page.mypanel.repaint();

            //暂停一会儿
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //让出turnSD

            synchronized (page) {
                page.turn = id % page.NUM_THREADS + 1; // 将turn设置为下一个线程
                page.flag[id] = false; // 退出临界区
            }
    System.out.println();
    }
}