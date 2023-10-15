package Algorithm_Module.Peterson.General;


public class Peterson_Filter {
    private final int N;  // Number of processes
    public boolean[][] flag;
    private int[] victim;
    private Peterson_General_Page page;

    public Peterson_Filter(int n, Peterson_General_Page page) {
        this.N = n;
        flag = new boolean[N+1][N+1];
        victim = new int[N+1];
        this.page = page;
    }

    public void lock(int processId) {
        for (int i = 1; i <= N; i++) {
            flag[processId][i] = true;
            victim[i] = processId;
            page.mymonitor.repaint();
            while (otherProcessWantsToEnter(processId, i) && victim[i] == processId) {

                page.mid_text.append("--进程" + processId + "成为受害者 且被拒绝进入临界区\n");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void unlock(int processId) {
        for (int i = N ; i >= 1; i--) {
            flag[processId][i] = false;
        }
    }

    private boolean otherProcessWantsToEnter(int processId, int level) {
        for (int j = 1; j <= N; j++) {
            if (j != processId) {
                if (flag[j][level]) {
                    return true;
                }
            }
        }
        return false;
    }
}
