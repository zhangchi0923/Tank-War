package cn.zhangchi.tank.decorator;

import cn.zhangchi.tank.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator {

    public TailDecorator(GameObject go){
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        // x、y是动态的，因此每次paint的时候都要更新
        this.x = go.x;
        this.y = go.y;
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(go.x,go.y,x+go.getWidth(),y+go.getHeight());
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
