package cn.zhangchi.tank.COR;

import cn.zhangchi.tank.Bullet;
import cn.zhangchi.tank.GameObject;
import cn.zhangchi.tank.Tank;
import cn.zhangchi.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            if(t.getRect().intersects(w.rect)){
                t.back();
            }
        }else if(o1 instanceof Wall && o2 instanceof Tank){
            collide(o2,o1);
        }
        return true;
    }
}
