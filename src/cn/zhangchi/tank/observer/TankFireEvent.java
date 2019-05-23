package cn.zhangchi.tank.observer;

import cn.zhangchi.tank.Tank;

public class TankFireEvent {
    Tank tank;

    public Tank getSource(){
        return tank;
    }

    public TankFireEvent(Tank tank){
        this.tank = tank;
    }
}
