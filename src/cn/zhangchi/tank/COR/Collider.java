package cn.zhangchi.tank.COR;

import cn.zhangchi.tank.GameObject;

public interface Collider {
    void collide(GameObject o1, GameObject o2);
}
