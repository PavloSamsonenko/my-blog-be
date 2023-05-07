package cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.grid.config.Config;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = Config.class)
public class CucumberConfig {}
