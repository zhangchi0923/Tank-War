package cn.zhangchi.tank;

/**
 * Main Driver
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        for(int i=0;i<5;i++){
            tf.tanks.add(new Tank(100+i*70,100,Dir.DOWN,Group.EVIL,tf));
        }
        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
