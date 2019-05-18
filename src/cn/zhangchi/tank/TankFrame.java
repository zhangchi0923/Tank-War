package cn.zhangchi.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;

import static com.sun.glass.ui.Cursor.setVisible;

// 继承Frame是为了重写其方法
public class TankFrame extends Frame {
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    Tank myTank = new Tank(200,200,Dir.DOWN,this);
    List<Bullet> bullets = new ArrayList<>();

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
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("number of bullets: "+bullets.size(),10,60);
        g.setColor(c);
        // 这块的设计思维是，将Tank封装后我们不能一用到哪个Tank的属性就访问哪个属性
        // 这样就破坏了封装性，而是将画出Tank的任务交给Tank 本身
        myTank.paint(g);

        // for循环要用简单循环，不能用iterator，会报ConcurrentException因为迭代器迭代时不能remove容器中的元素
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
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
                myTank.fire();
            default:
                break;
            }

            setMainTankDir();
        }
        // 如果没按任何方向键就不设定方向
        private void setMainTankDir(){
            if(!bL && !bR && !bU && !bD){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }

    }
}
