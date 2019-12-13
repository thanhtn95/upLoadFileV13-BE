package zone.god.uploadv13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import zone.god.uploadv13.service.CardService;
import zone.god.uploadv13.service.CardServiceImpl;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableWebMvc
@EnableTransactionManagement
public class Uploadv13Application {

    public static void main(String[] args) {
        SpringApplication.run(Uploadv13Application.class, args);
    }

    @Bean
    public CardService cardService() {
        return new CardServiceImpl();
    }

}
