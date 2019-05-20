package cn.zhangchi.tank;

import java.awt.*;

/**
 * 作业：生成一个敌人坦克，并消灭它
 */
public class EnemyTank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private boolean isAlive = true;
    private TankFrame tf;

    public EnemyTank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }
    // 一开炮敌方坦克就没了，咋回事儿？？
    public void setAlive(TankFrame tf) {
        if(tf.bullets.size() == 0){this.isAlive = true;}
        else{
            for(int i=0;i<tf.bullets.size();i++){
                int boundX = tf.bullets.get(i).getX();
                int boundY = tf.bullets.get(i).getY();
                if(Math.abs((boundX-this.x))<Bullet.WIDTH/2 | Math.abs((boundY-this.y))< Bullet.HEIGHT/2){
                    this.isAlive = false;
                }
            }
        }
    }

    public void paint(Graphics g){
        setAlive(tf);
        if(!isAlive) return;
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,50,50);
        g.setColor(c);
    }
}
