package cn.zhangchi.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private final int SPEED = 5;
    private boolean moving = true;
    private boolean isAlive = true;
    private Random random = new Random();
    private Group group = Group.EVIL;
    private TankFrame tf;
    static final int WIDTH = ResourceManager.tankL.getWidth();
    static final int HEIGHT = ResourceManager.tankL.getHeight();

    Rectangle rect = new Rectangle();

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        switch(dir){
        case LEFT:
            g.drawImage(group == Group.EVIL? ResourceManager.tankL:ResourceManager.goodTankL,x,y,null);
            break;
        case RIGHT:
            g.drawImage(group == Group.EVIL? ResourceManager.tankR:ResourceManager.goodTankR,x,y,null);
            break;
        case UP:
            g.drawImage(group == Group.EVIL? ResourceManager.tankU:ResourceManager.goodTankU,x,y,null);
            break;
        case DOWN:
            g.drawImage(group == Group.EVIL? ResourceManager.tankD:ResourceManager.goodTankD,x,y,null);
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

        if(group == Group.EVIL && random.nextInt(100)>95){
            fire();
        }
        if(group == Group.EVIL && random.nextInt(100)>85){
            randDir();
        }

        boundsCheck();
        // update rect
        rect.x = this.x;
        rect.y = this.y;

    }

    private void boundsCheck() {
        if(this.x < 0) x = 0;
        if(this.y < 30) y = 30;
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.tf));

    }

    public void die() {
        this.isAlive = false;
    }
}
