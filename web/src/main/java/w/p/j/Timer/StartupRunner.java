package w.p.j.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * Created by everythingok on 2016/3/13.
 */
public class StartupRunner implements CommandLineRunner {
    protected final Logger logger = LoggerFactory.getLogger(StartupRunner.class);
    @Override
    public void run(String... strings) throws Exception {
        logger.info("hello");
    }

}
