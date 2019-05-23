package cn.zhangchi.tank;

import java.awt.*;

public class Explode extends GameObject{
    public static final int WIDTH = ResourceManager.getInstance().getExplodes()[0].getWidth();
    public static final int HEIGHT = ResourceManager.getInstance().getExplodes()[0].getHeight();
    private boolean isAlive = true; // 判断子弹是否存活，非存活状态下要在容器中删除，否则会有内存泄漏的问题
    private int step = 0;


    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g) {
        if(!isAlive){
            GameModel.getInstance().remove(this);
        }
        if(step >= ResourceManager.getInstance().getExplodes().length){
            isAlive = false;
        }else{
            g.drawImage(ResourceManager.getInstance().getExplodes()[step++],x,y,null);
        }
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}

