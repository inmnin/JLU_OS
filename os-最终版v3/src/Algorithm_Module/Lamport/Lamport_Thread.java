package Algorithm_Module.Lamport;

public class Lamport_Thread implements Runnable{
    int id;
    Lamport_Page page;
    public Lamport_Thread(int id,Lamport_Page page){
        this.id = id;
        this.page = page;
    }
    public void run(){

        //先停顿一会儿
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //取号过程
        page.Entering[id] = true;
        page.Number[id] = max() + 1;
        page.self_output.taken_number_output(id,page.Number[id]);
        page.Entering[id] = false;

        //有人想取号我就稍后，但是由于并发控制会出现两人取到同号的情况
        for (int j = 1; j<= page.NUM_THREADS; j++){
            while(page.Entering[j]){
                //当然也可以忙式等待，这里直接采取睡眠一段时间的方式来节省资源
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                page.self_output.is_taking_number_output(id);
            }

            //当有人比我号小或者我们同号但是它进程id小时，我就让出等待（这里用id号来判断其实是没啥道理的）
            //若走一圈所有人都没有这个情况（可能有人在我循环过去之后又取到号了，但是这个号一定要比我手里的号要小）
            while((page.Number[j] != 0) && ((page.Number[j] < page.Number[id]) || ((page.Number[j] == page.Number[id]) &&(j < id)))){
                //随机等待一个时间
                int max=3000,min=2000;
                int ran1 = (int) (Math.random()*(max-min)+min);
                try {
                    Thread.sleep(ran1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                page.outPut.waiting_output(id);
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        page.self_output.ready_output(id,page.Number[id]);

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
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
        //注销面包店号
        page.Number[id] = 0;

    }

    //取号函数
    private int max(){
        int max = -1;
        for(int i = 1; i<= page.NUM_THREADS; i++){
            if(max < page.Number[i])
                max = page.Number[i];
        }
        return max;
    }

}