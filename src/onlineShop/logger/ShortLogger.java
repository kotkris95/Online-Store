package onlineShop.logger;

import java.text.DateFormat;
import java.util.Date;

public class ShortLogger implements Logger {
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    public void info(String msg) {
        Date now = new Date();
        System.out.printf("[%s] %s", dateFormat.format(now), msg);
    }

    @Override
    public void debug(String msg) {

    }

    @Override
    public void warning(String msg) {

    }

    @Override
    public void error(String msg) {

    }
}
