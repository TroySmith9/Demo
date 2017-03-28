package log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.util.SystemPropertyUtils;

/**
 * Created by yiban on 2017/3/18.
 */
public class ApacheLog4j {
    public static void main(String[] args) {
        // 1. create log  
        Logger log = Logger.getLogger(ApacheLog4j.class);
        // 2. get log config file  
        System.out.println(SystemPropertyUtils.resolvePlaceholders("log4j.properties"));
        PropertyConfigurator.configure("G:\\workspace\\workspace4idea\\Demo\\resource\\log4j.properties");
        // 3. start log  
        log.debug("Here is some DEBUG");
        log.info("Here is some INFO");
        log.warn("Here is some WARN");
        log.error("Here is some ERROR");
        log.fatal("Here is some FATAL");
    }
}
