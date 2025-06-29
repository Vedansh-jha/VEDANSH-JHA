// ===============================
// Exercise 1: Logging Error and Warning Levels
// ===============================
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.error("This is an error message");
        logger.warn("This is a warning message");
    }
}

// ===============================
// Exercise 2: Parameterized Logging
// ===============================
class ParameterizedLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String user = "Alice";
        int items = 5;

        logger.info("User {} placed an order with {} items.", user, items);
        logger.debug("Debugging session for user={} and itemCount={}", user, items);
    }
}

// ===============================
// Exercise 3: Logging with Multiple Appenders
// ===============================
class AppenderLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.info("This INFO log appears in both console and file.");
        logger.warn("This is a WARN log");
        logger.error("This is an ERROR log");
    }
}
