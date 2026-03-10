package common;

import java.io.File;

public class GlobalVariable {
    public static ThreadLocal<String> platform = new ThreadLocal<>();
    public static int timeWait = 10;

    public static String projectDir = System.getProperty("user.dir") + File.separator;

    public static String getPlatform(){
        return platform.get();
    }

    public static void setPlatform(String platformString){
        platform.set(platformString);
    }

}
