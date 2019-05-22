package cn.zhangchi.tank.COR;

import cn.zhangchi.tank.Bullet;
import cn.zhangchi.tank.GameObject;
import cn.zhangchi.tank.Tank;

public class BulletTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            b.collidewith(t);
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2,o1);
        }else return;
    }
}
