package com.juan.sanchez;

import com.juan.sanchez.service.AuthorService;
import com.juan.sanchez.service.BookService;
import com.juan.sanchez.utils.SpringFrameworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class SpringBootJdbcApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringBootJdbcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);
	}

	@Bean
	@Order(1)
	CommandLineRunner greeting() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				logger.info("Hello from Spring Boot JDBC");
			}
		};
	}

	@Bean
	@Order(2)
	CommandLineRunner profilesAndBeansDefinitionsReport(ApplicationContext ctx) {
		return args -> SpringFrameworkUtils.profilesAndBeansDefinitionsReport(ctx);
	}

	@Bean
	@Order(3)
	CommandLineRunner authorServiceReporting(ApplicationContext ctx) {
		return args -> {
			System.out.println("");
			System.out.println("-------------------");
			System.out.println(" AuthorService ");
			System.out.println("-------------------");
			System.out.println("");

			AuthorService authorService = ctx.getBean(AuthorService.class);

			System.out.println("");
			System.out.println("--------------------");
			System.out.println("Report - 'findById'");
			System.out.println("--------------------");
			System.out.println("");
			authorService.report(authorService.findById(1));
			authorService.report(authorService.findById(5));
			authorService.report(authorService.findById(7));

			System.out.println("");
			System.out.println("-----------------------------------");
			System.out.println("Report - 'findByIdWithBooks'");
			System.out.println("-----------------------------------");
			System.out.println("");
			authorService.reportWithBooks(authorService.findByIdWithBooks(4));

			System.out.println("");
			System.out.println("-------------------");
			System.out.println("Report - 'findAll'");
			System.out.println("-------------------");
			System.out.println("");
			authorService.report(authorService.findAll());

			System.out.println("");
			System.out.println("-----------------------------------");
			System.out.println("Report - 'findAllWithBooks'");
			System.out.println("-----------------------------------");
			System.out.println("");
			authorService.reportWithBooks(authorService.findAllWithBooks());

			System.out.println("");
			System.out.println("----------------------");
			System.out.println("Report - 'count'");
			System.out.println("----------------------");
			System.out.println("");
			authorService.report(authorService.count());
		};
	}

	@Bean
	@Order(4)
	CommandLineRunner bookServiceReporting(ApplicationContext ctx) {
		return args -> {
			System.out.println("");
			System.out.println("-------------------");
			System.out.println(" BookService ");
			System.out.println("-------------------");
			System.out.println("");

			// Gets the bean that returns BookService
			BookService bookService = ctx.getBean(BookService.class);

			System.out.println("");
			System.out.println("--------------------");
			System.out.println("Report - 'findById'");
			System.out.println("--------------------");
			System.out.println("");
			bookService.report(bookService.findById(1));
			bookService.report(bookService.findById(5));
			bookService.report(bookService.findById(7));

			System.out.println("");
			System.out.println("-------------------");
			System.out.println("Report - 'findAll'");
			System.out.println("-------------------");
			System.out.println("");
			bookService.report(bookService.findAll());

			System.out.println("");
			System.out.println("----------------------");
			System.out.println("Report - 'count'");
			System.out.println("----------------------");
			System.out.println("");
			bookService.report(bookService.count());
		};
	}

}
