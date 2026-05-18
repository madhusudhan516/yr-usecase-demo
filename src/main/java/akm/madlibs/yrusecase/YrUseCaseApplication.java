package akm.madlibs.yrusecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("")
public class YrUseCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(YrUseCaseApplication.class, args);
    }

    @GetMapping("")
    public String welcomeMessage() {
        return "Welcome to Yr Usecase Application!";
    }

}
