package cn.zhangchi.tank.COR;

import cn.zhangchi.tank.GameModel;
import cn.zhangchi.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
