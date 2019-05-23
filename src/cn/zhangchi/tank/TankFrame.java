package cn.zhangchi.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


// 继承Frame是为了重写其方法
public class TankFrame extends Frame {

    GameModel gm = GameModel.getInstance();

    static final int GAME_WIDTH = PropertyManager.getInt("gameWidth");
    static final int GAME_HEIGHT = PropertyManager.getInt("gameHeight");


    public TankFrame(){
        setVisible(true);
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");

        this.addKeyListener(new MyKeyListener() {});

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // 双缓存去闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    // 系统自动调用paint方法，用于画面更新;每次调用paint后先清理画面内容
    // 那么怎么调用呢？需要有个东西来触发paint方法
    // 主程序中的repaint()方法调用paint()
    public void paint(Graphics g){

        gm.paint(g);

//        eTank.paint(g);
    }
    // 新建一个类处理键盘输入，键盘的按下、松开改变的是dir的值
    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;
        @Override
        public void keyPressed(KeyEvent e) {
            // 可是程序怎么知道哪个键被按下了？
            int key = e.getKeyCode();
            // 根据键盘输入调整paint中x、y的值，从而实现正方形的移动
            switch(key){
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            default:
                break;
            }
//            x += 100;
//            repaint();    // 刷新窗口，本方法会自动调用paint()，因为我们没有Graphic

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch(key){
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_CONTROL:
                gm.getMyTank().handleFireKey();
                break;
            default:
                break;
            }

            setMainTankDir();
        }
        // 如果没按任何方向键就不设定方向
        private void setMainTankDir(){
            if(!bL && !bR && !bU && !bD){
                gm.getMyTank().setMoving(false);
            }else {
                gm.getMyTank().setMoving(true);
                if (bL) gm.getMyTank().setDir(Dir.LEFT);
                if (bR) gm.getMyTank().setDir(Dir.RIGHT);
                if (bU) gm.getMyTank().setDir(Dir.UP);
                if (bD) gm.getMyTank().setDir(Dir.DOWN);
            }
        }

    }
}
