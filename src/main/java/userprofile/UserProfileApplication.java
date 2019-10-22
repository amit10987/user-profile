package userprofile;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UserProfileApplication {

    @Bean
    public SampleDataSet dataSet() {
        return new SampleDataSet();
    }

    @Bean
    public SparkConf conf() {
        return new SparkConf().setAppName("userprofile").setMaster("local[*]");
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(conf());
    }
    @Bean
    public SQLContext sqlContext() {
        return new SQLContext(javaSparkContext());
    }


    public static void main(String[] args) {
        SpringApplication.run(UserProfileApplication.class, args);
    }

}
