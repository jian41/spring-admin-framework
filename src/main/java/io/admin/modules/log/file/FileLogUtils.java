package io.admin.modules.log.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class FileLogUtils {


    public static Logger getLogger() {
        return LoggerFactory.getLogger(FileLogConfig.LOGGER_NAME);
    }


    public static void start(String key) {
        MDC.put(FileLogConfig.DISCRIMINATOR_KEY, key);
    }

    public static void stop() {
        MDC.clear();
    }


}
