package cn.zhangchi.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 3;
    private int x, y;
    private Dir dir;
    private TankFrame tf;
    static final int WIDTH = 20, HEIGHT = 12;
    private boolean isAlive = true; // 判断子弹是否存活，非存活状态下要在容器中删除，否则会有内存泄漏的问题
    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g){
        if(!isAlive){
            tf.bullets.remove(this);
        }
        switch(dir){
        case LEFT:
            g.drawImage(ResourceManager.bulletL,x,y,null);
            break;
        case RIGHT:
            g.drawImage(ResourceManager.bulletR,x,y,null);
            break;

        case UP:
            g.drawImage(ResourceManager.bulletU,x,y,null);
            break;

        case DOWN:
            g.drawImage(ResourceManager.bulletD,x,y,null);
            break;
        }

        move();
    }
    private void move(){
        switch(dir){
        case LEFT:
            x -= SPEED;
            break;
        case RIGHT:
            x += SPEED;
            break;

        case UP:
            y -= SPEED;
            break;

        case DOWN:
            y += SPEED;
            break;
        }
        // 对TankFrame的引用在这里用上
        if(x<0 | y<0 | x>TankFrame.GAME_WIDTH | y>TankFrame.GAME_HEIGHT) isAlive = false;

    }
}
