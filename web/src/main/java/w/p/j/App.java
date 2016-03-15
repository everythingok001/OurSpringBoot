package w.p.j;


import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import w.p.j.Timer.StartupRunner;


@SpringBootApplication
@EnableAdminServer
public class App  extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public StartupRunner schedulerRunner() {
        return new StartupRunner();
    }
}
