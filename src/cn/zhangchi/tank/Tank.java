package cn.zhangchi.tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private final int SPEED = 10;
    private boolean moving = false;
    private TankFrame tf;

    public Tank(int x, int y, Dir dir,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
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
        tf.bullets.add(new Bullet(this.x,this.y,this.dir,this.tf));

    }
}
