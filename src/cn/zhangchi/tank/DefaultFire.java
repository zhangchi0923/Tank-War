package cn.zhangchi.tank;

import cn.zhangchi.tank.decorator.RectDecorator;
import cn.zhangchi.tank.decorator.TailDecorator;

public class DefaultFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        GameModel.getInstance().add(new RectDecorator(new TailDecorator(new Bullet(bX,bY,tank.getDir(),tank.getGroup()))));

    }
}
