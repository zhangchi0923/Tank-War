package cn.zhangchi.tank.COR;

import cn.zhangchi.tank.Bullet;
import cn.zhangchi.tank.GameObject;
import cn.zhangchi.tank.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            if(b.getRect().intersects(w.rect)){
                b.die();
            }
        }else if(o1 instanceof Wall && o2 instanceof Bullet){
            collide(o2,o1);
        }
        return true;
    }
}
