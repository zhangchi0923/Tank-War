package cn.zhangchi.tank;

/**
 * Main Driver
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        int initTankCount = Integer.parseInt((String)PropertyManager.get("initTankCount"));

        for(int i=0;i<initTankCount;i++){
            tf.tanks.add(new Tank(100+i*70,100,Dir.DOWN,Group.EVIL,tf));
        }
        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
