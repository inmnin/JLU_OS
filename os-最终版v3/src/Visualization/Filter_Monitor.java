package Visualization;

import java.awt.*;

public class Filter_Monitor extends Panel {
    private final int MAX_SIZE = 170; // 最大尺寸
    private boolean[][] flag;
    private int circleDiameter;
    private int rows;
    private int cols;

    public Filter_Monitor(boolean[][] flag){
        this.flag = flag;
        this.rows = flag.length;
        this.cols = flag[0].length; // 假设所有行的长度都相同


        // 根据数组大小动态地确定每个圆的直径
        this.circleDiameter = Math.min(MAX_SIZE / cols, MAX_SIZE / rows);
    }

    public void paint(Graphics graphics) {
//        graphics.drawString(" 监督flag数组",0,2);
        synchronized (flag) { // 互斥地访问flag数组
            for(int i = 1; i < rows; i++) {
                for(int j = 1; j < cols; j++) {
                    int x = (j-1) * circleDiameter+40;
                    int y = (i-1) * circleDiameter+10;


                    if (j == 1) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawString("进程" + i + ":", 0, y + circleDiameter/2);
                    }
                    if(flag[i][j]) {
                        graphics.setColor(Color.BLACK);
                    } else {
                        graphics.setColor(Color.WHITE);
                    }


                    graphics.fillOval(x, y, circleDiameter, circleDiameter);
                    graphics.setColor(Color.BLACK);
                    graphics.drawOval(x, y, circleDiameter, circleDiameter);
                }
            }
        }
    }
}
