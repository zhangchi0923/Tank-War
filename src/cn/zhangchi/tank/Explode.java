package cn.zhangchi.tank;

import java.awt.*;

public class Explode {
    private int x, y;
    static final int WIDTH = ResourceManager.getInstance().getExplodes()[0].getWidth();
    static final int HEIGHT = ResourceManager.getInstance().getExplodes()[0].getHeight();
    private boolean isAlive = true; // 判断子弹是否存活，非存活状态下要在容器中删除，否则会有内存泄漏的问题
    private int step = 0;

    GameModel gm = null;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g) {
        if(!isAlive){
            gm.explodes.remove(this);
        }
        if(step >= ResourceManager.getInstance().getExplodes().length){
            isAlive = false;
        }else{
            g.drawImage(ResourceManager.getInstance().getExplodes()[step++],x,y,null);
        }
    }
}

