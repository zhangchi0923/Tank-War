package cn.zhangchi.tank;

import java.awt.*;

public class Explode {
    private int x, y;
    private TankFrame tf;
    static final int WIDTH = ResourceManager.explodes[0].getWidth();
    static final int HEIGHT = ResourceManager.explodes[0].getHeight();
    private boolean isAlive = true; // 判断子弹是否存活，非存活状态下要在容器中删除，否则会有内存泄漏的问题
    private int step = 0;
    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++],x,y,null);
        if(step >= ResourceManager.explodes.length){
            step = 0;
        }
    }
}

