package userprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "userprofile/repository")
public class UserProfileApplication {

    @Bean
    public SampleDataSet dataSet() {
        return new SampleDataSet();
    }


    public static void main(String[] args) {
        SpringApplication.run(UserProfileApplication.class, args);
    }

}
