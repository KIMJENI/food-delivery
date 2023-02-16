package food.delivery.common;


import food.delivery.OrderingApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { OrderingApplication.class })
public class CucumberSpingConfiguration {
    
}
