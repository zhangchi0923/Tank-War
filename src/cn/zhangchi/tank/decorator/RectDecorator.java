package cn.zhangchi.tank.decorator;

import cn.zhangchi.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator {

    public RectDecorator(GameObject go){
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
        g.drawRect(go.x,go.y,go.getWidth()+1,go.getHeight()+1);
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
