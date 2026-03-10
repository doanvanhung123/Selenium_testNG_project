package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class LogUtils {
    //Initialize Log4j instance
    public static ThreadLocal<Logger> log = new ThreadLocal<>();

    //Info Level Logs


    public static void info(String message) {
        log.get().info(message);
    }

    public static void info(Object object) {
        log.get().info(object);
    }

    //Warn Level Logs
    public static void warn(String message) {
        log.get().info(message);
    }

    public static void warn(Object object) {
        log.get().info(object);
    }

    //Error Level Logs
    public static void error(String message) {
        log.get().info(message);
    }

    public static void error(Object object) {
        log.get().info(object);
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        log.get().info(message);
    }

    //Debug Level Logs
    public static void debug(String message) {
        log.get().info(message);
    }

    public static void debug(Object object) {
        log.get().info(object);
    }
}
