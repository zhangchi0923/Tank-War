package cn.zhangchi.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(200,200,Dir.DOWN,Group.GOOD,this);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel(){
        int initTankCount = PropertyManager.getInt("initTankCount");

        for(int i=0;i<initTankCount;i++){
            tanks.add(new Tank(100+i*70,100,Dir.DOWN,Group.EVIL,this));
        }

    }

    public Tank getMyTank() {
        return myTank;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        // 显示子弹数量
        g.drawString("number of bullets: "+bullets.size(),10,60);
        // 显示敌人坦克数量
        g.drawString("number of enemies: "+tanks.size(),10,80);
        g.setColor(c);

        // 这块的设计思维是，将Tank封装后我们不能一用到哪个Tank的属性就访问哪个属性
        // 这样就破坏了封装性，而是将画出Tank的任务交给Tank 本身
        myTank.paint(g);
//        e.paint(g);

        // for循环要用简单循环，不能用iterator，会报ConcurrentException因为迭代器迭代时不能remove容器中的元素
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }

        for(int i=0;i<tanks.size();i++){
            tanks.get(i).paint(g);
        }

        for(int i=0;i<bullets.size();i++){
            for(int j=0;j<tanks.size();j++){
                bullets.get(i).collidewith(tanks.get(j));
            }
        }

        for(int i=0;i<explodes.size();i++){
            explodes.get(i).paint(g);
        }

    }
}
