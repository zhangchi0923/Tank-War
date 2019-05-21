package cn.zhangchi.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    static Properties props = new Properties();

    static{
        try {
            props.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getInt(String key){
        if(props == null) return 0;
        return Integer.parseInt((String)props.get(key));
    }

    public static String getString(String key){
        if(props == null) return null;
        return (String) props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyManager.getInt("initTankCount"));
    }
}
