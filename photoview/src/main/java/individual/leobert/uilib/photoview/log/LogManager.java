package individual.leobert.uilib.photoview.log;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.log </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> LogManager </p>
 * <p><b>Description:</b> logger manager </p>
 * Created by leobert on 2017/5/3.
 */

public class LogManager {
    private static Logger logger = new LoggerDefault();

    public LogManager() {
    }

    public static void setLogger(Logger newLogger) {
        logger = newLogger;
    }

    public static Logger getLogger() {
        return logger;
    }
}
