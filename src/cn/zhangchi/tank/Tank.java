package cn.zhangchi.tank;

import cn.zhangchi.tank.observer.TankFireEvent;
import cn.zhangchi.tank.observer.TankFireHandler;
import cn.zhangchi.tank.observer.TankFireObserver;

import java.awt.*;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class Tank extends GameObject{
    public int prevX, prevY;
    private Dir dir = Dir.DOWN;
    private final int SPEED = PropertyManager.getInt("tankSpeed");
    private boolean moving = true;
    private boolean isAlive = true;
    private Random random = new Random();
    private Group group = Group.EVIL;
    public static final int WIDTH = ResourceManager.getInstance().getTankL().getWidth();
    public static final int HEIGHT = ResourceManager.getInstance().getTankL().getHeight();

    Rectangle rect = new Rectangle();

    FireStrategy fs;

    public Tank(int x, int y, Dir dir,Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(group == Group.GOOD){
            String goodFsName = (String) PropertyManager.props.get("goodFs");
            try {
                fs = (FireStrategy) Class.forName(goodFsName).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            String badFsName = (String) PropertyManager.props.get("badFs");
            try {
                fs = (FireStrategy) Class.forName(badFsName).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
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

    public Dir getDir() {
        return dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g){
        if(!isAlive){
            GameModel.getInstance().remove(this);
        }
        switch(dir){
        case LEFT:
            g.drawImage(group == Group.EVIL? ResourceManager.getInstance().getTankL():ResourceManager.getInstance().getGoodTankL(),x,y,null);
            break;
        case RIGHT:
            g.drawImage(group == Group.EVIL? ResourceManager.getInstance().getTankR():ResourceManager.getInstance().getGoodTankR(),x,y,null);
            break;
        case UP:
            g.drawImage(group == Group.EVIL? ResourceManager.getInstance().getTankU():ResourceManager.getInstance().getGoodTankU(),x,y,null);
            break;
        case DOWN:
            g.drawImage(group == Group.EVIL? ResourceManager.getInstance().getTankD():ResourceManager.getInstance().getGoodTankD(),x,y,null);
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

    private List<TankFireObserver> observers = Arrays.asList(new TankFireHandler());
    public void handleFireKey(){
        TankFireEvent event = new TankFireEvent(this);
        for(TankFireObserver o:observers){
            o.actionOnFire(event);
        }
    }

    private void move() {
        prevX = x;
        prevY = y;

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
    public void back(){
        x = prevX;
        y = prevY;
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
        fs.fire(this);
    }

    public void die() {
        this.isAlive = false;
    }
}
