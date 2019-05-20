package cn.zhangchi.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 1;
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
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);

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
