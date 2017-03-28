package log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by yiban on 2017/3/18.
 */
public class CommonsLogging {

    static Log log = LogFactory.getLog(CommonsLogging.class);

    public static void main(String[] args) {
        log.debug("Here is some DEBUG");
        log.info("Here is some INFO");
        log.warn("Here is some WARN");
        log.error("Here is some ERROR");
        log.fatal("Here is some FATAL");
    }
}
