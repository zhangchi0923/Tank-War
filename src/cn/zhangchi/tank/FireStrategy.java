package cn.zhangchi.tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank tank);
}
