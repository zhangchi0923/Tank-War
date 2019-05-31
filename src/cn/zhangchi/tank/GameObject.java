package cn.zhangchi.tank;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    // 为啥protected之后在子类中无法访问？
    public int x,y;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
