package cn.zhangchi.tank;

import com.sun.xml.internal.bind.v2.TODO;

import java.awt.*;

public class Bullet {
    private static final int SPEED = PropertyManager.getInt("bulletSpeed");
    private int x, y;
    private Dir dir;
    private Group group = Group.EVIL;
    static final int WIDTH = ResourceManager.getInstance().getBulletL().getWidth();
    static final int HEIGHT = ResourceManager.getInstance().getBulletL().getHeight();
    private boolean isAlive = true; // 判断子弹是否存活，非存活状态下要在容器中删除，否则会有内存泄漏的问题

    Rectangle rect = new Rectangle();
    GameModel gm = null;
    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        gm.bullets.add(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g){
        if(!isAlive){
            gm.bullets.remove(this);
        }
        switch(dir){
        case LEFT:
            g.drawImage(ResourceManager.getInstance().getBulletL(),x,y,null);
            break;
        case RIGHT:
            g.drawImage(ResourceManager.getInstance().getBulletR(),x,y,null);
            break;

        case UP:
            g.drawImage(ResourceManager.getInstance().getBulletU(),x,y,null);
            break;

        case DOWN:
            g.drawImage(ResourceManager.getInstance().getBulletD(),x,y,null);
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

        // update rect
        rect.x = this.x;
        rect.y = this.y;
        // 对TankFrame的引用在这里用上
        if(x<0 | y<0 | x>TankFrame.GAME_WIDTH | y>TankFrame.GAME_HEIGHT) isAlive = false;

    }

    public void collidewith(Tank tank) {
        if(this.group == tank.getGroup()) return;
        //ToDo:只用一个rect记录边界，而不是每次都new出新rect
        if (this.rect.intersects(tank.rect)){
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            gm.explodes.add(new Explode(eX,eY,this.gm));
        }
    }

    private void die() {
        this.isAlive = false;
    }
}
