package cn.zhangchi.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManager {
    private BufferedImage tankL,tankR,tankU,tankD,goodTankL,goodTankR,goodTankU,goodTankD;
    private BufferedImage bulletL,bulletR,bulletU,bulletD;
    private BufferedImage[] explodes = new BufferedImage[16];

    private static final ResourceManager INSTANCE = new ResourceManager();

    private ResourceManager(){
        try {
            tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD = ImageUtil.rotateImage(tankU,180);

            goodTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankD = ImageUtil.rotateImage(goodTankU,180);

            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            for(int i=0;i<16;i++){
                explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 业务方法
    public BufferedImage getTankL() {
        return tankL;
    }

    public BufferedImage getTankR() {
        return tankR;
    }

    public BufferedImage getTankU() {
        return tankU;
    }

    public BufferedImage getTankD() {
        return tankD;
    }

    public BufferedImage getGoodTankL() {
        return goodTankL;
    }

    public BufferedImage getGoodTankR() {
        return goodTankR;
    }

    public BufferedImage getGoodTankU() {
        return goodTankU;
    }

    public BufferedImage getGoodTankD() {
        return goodTankD;
    }

    public BufferedImage getBulletL() {
        return bulletL;
    }

    public BufferedImage getBulletR() {
        return bulletR;
    }

    public BufferedImage getBulletU() {
        return bulletU;
    }

    public BufferedImage getBulletD() {
        return bulletD;
    }

    public BufferedImage[] getExplodes() {
        return explodes;
    }
    // getInstance() 方法
    public static ResourceManager getInstance(){
        return INSTANCE;
    }
}
