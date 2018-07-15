package cn.socbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class BlogWebApplication {

	public static void main(String[] args) {
		System.out.println("hello wrold");
		SpringApplication.run(BlogWebApplication.class, args);
	}

}
