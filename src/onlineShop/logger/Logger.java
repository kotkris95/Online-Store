package onlineShop.logger;

public interface Logger {
    void info(String msg);

    void debug(String msg);

    void warning(String msg);

    void error(String msg);

    public enum Type implements Logger {
        FULL(new FullLogger()), SHORT(new ShortLogger());
        private Logger logger;

        Type(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void info(String msg) {
            logger.info(msg);
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
}
