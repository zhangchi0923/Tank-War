package cn.zhangchi.tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private final int SPEED = 10;
    private boolean moving = false;
    private boolean isAlive = true;
    private TankFrame tf;
    static final int WIDTH = ResourceManager.tankL.getWidth();
    static final int HEIGHT = ResourceManager.tankL.getHeight();

    public Tank(int x, int y, Dir dir,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(Dir dir){
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public void paint(Graphics g){
        if(!isAlive){
            tf.tanks.remove(this);
        }
        Color c = g.getColor();
        switch(dir){
        case LEFT:
            g.drawImage(ResourceManager.tankL,x,y,null);
            break;
        case RIGHT:
            g.drawImage(ResourceManager.tankR,x,y,null);
            break;
        case UP:
            g.drawImage(ResourceManager.tankU,x,y,null);
            break;
        case DOWN:
            g.drawImage(ResourceManager.tankD,x,y,null);
            break;
        }

        move();

    }

    private void move() {
        if(!moving) return;
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
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bX,bY,this.dir,this.tf));

    }

    public void die() {
        this.isAlive = false;
    }
}
