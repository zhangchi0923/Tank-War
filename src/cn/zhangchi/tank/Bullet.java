package cn.zhangchi.tank;

import java.awt.*;

public class Bullet extends GameObject{
    private static final int SPEED = PropertyManager.getInt("bulletSpeed");
    private Dir dir;
    private Group group = Group.EVIL;
    static final int WIDTH = ResourceManager.getInstance().getBulletL().getWidth();
    static final int HEIGHT = ResourceManager.getInstance().getBulletL().getHeight();
    private boolean isAlive = true; // 判断子弹是否存活，非存活状态下要在容器中删除，否则会有内存泄漏的问题

    Rectangle rect = new Rectangle();
    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        GameModel.getInstance().add(this);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public void paint(Graphics g){
        if(!isAlive){
            GameModel.getInstance().remove(this);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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

    public void die() {
        this.isAlive = false;
    }
}
