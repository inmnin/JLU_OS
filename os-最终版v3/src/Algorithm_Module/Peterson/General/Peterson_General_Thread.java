package Algorithm_Module.Peterson.General;


public class Peterson_General_Thread implements Runnable{
    int id;
    Peterson_General_Page page;
    Peterson_Filter mylock;
    public Peterson_General_Thread(int id, Peterson_General_Page page, Peterson_Filter filter){
        this.id = id;
        this.page = page;
        mylock = filter;
    }
    public void run(){

        //先停顿一会儿
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //有人想取号我就稍后，但是由于并发控制会出现两人取到同号的情况

        mylock.lock(id);

        page.outPut.visiting_output(id);

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.outPut.ready_output(id);
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

        //接触受害者锁
        mylock.unlock(id);

    }

}