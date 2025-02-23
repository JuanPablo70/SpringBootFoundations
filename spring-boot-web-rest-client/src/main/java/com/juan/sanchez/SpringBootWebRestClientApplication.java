package com.juan.sanchez;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.factory.AuthorFactory;
import com.juan.sanchez.utils.ResponseEntityUtils;
import com.juan.sanchez.web.rest.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@SpringBootApplication
//@EnableConfigurationProperties(ServerRemoteProperties.class)
@ConfigurationPropertiesScan(basePackages = "com.juan.sanchez.config")
public class SpringBootWebRestClientApplication {

	@Value("${spring.profiles.active}")
	private String profilesActive;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebRestClientApplication.class, args);
	}

	@Bean
	@Order(1)
	@Profile("rest-client-v1")
	CommandLineRunner reportingAuthorRestClientV1(AuthorRestClientV1 authorRestClientV1) {
		return args -> {
			System.out.println("Reporting Author REST Client V1");

			System.out.println(" findById");
			Author author = authorRestClientV1.findById(2);
			System.out.println("   " + author.toString());

			System.out.println(" findAll");
			try {
				Collection<Author> authors = authorRestClientV1.findAll();
				System.out.println("   " + authors.toString());
				authors.forEach(a -> System.out.println("   " + a));
			} catch (Exception ex) {
				System.out.println("  Exception: ");
				System.out.println("   " + ex.getMessage());
			}

			System.out.println(" save");
			author = AuthorFactory.create(111);
			authorRestClientV1.save(author);

			System.out.println(" update");
			authorRestClientV1.update(AuthorFactory.update(author));

			if(profilesActive.contains("delete")) {
				System.out.println(" deleteById");
				authorRestClientV1.deleteById(author.getId());
			}
		};
	}

	@Bean
	@Order(2)
	@Profile("rest-client-v2")
	CommandLineRunner reportingAuthorRestClientV2(AuthorRestClientV2 authorRestClientV2) {
		return args -> {
			System.out.println("Reporting Author REST Client V2");

			System.out.println(" findById");
			ResponseEntity<Author> responseFindById = authorRestClientV2.findById(2);
			ResponseEntityUtils.reportFindById(responseFindById);

			System.out.println(" findAll");
			try {
				ResponseEntity<Collection<Author>> responseFindAll = authorRestClientV2.findAll();
				ResponseEntityUtils.reportFindAll(responseFindAll);
			} catch (Exception ex) {
				System.out.println("  Exception: ");
				System.out.println("   " + ex.getMessage());
			}

			System.out.println(" save");
			Author author = AuthorFactory.create(222);
			ResponseEntity<Void> responseSave = authorRestClientV2.save(author);
			ResponseEntityUtils.reportSave(responseSave);

			System.out.println(" update");
			ResponseEntity<Void> responseUpdate = authorRestClientV2.update(AuthorFactory.update(author));
			if (responseUpdate != null) {
				ResponseEntityUtils.reportUpdate(responseUpdate);
			} else {
				System.out.println("  null\n");
			}

			if(profilesActive.contains("delete")) {
				System.out.println(" deleteById");
				ResponseEntity<Void> responseDelete = authorRestClientV2.deleteById(author.getId());
				if(responseDelete != null) {
					ResponseEntityUtils.reportDelete(responseDelete);
				} else {
					System.out.println("  null\n");
				}
			}
		};
	}

	@Bean
	@Order(3)
	@Profile("rest-client-v3")
	CommandLineRunner reportingAuthorRestClientV3(AuthorRestClientV3 authorRestClientV3) {
		return args -> {
			System.out.println("Reporting Author REST Client V3");

			System.out.println(" findById");
			ResponseEntity<Author> responseFindById = authorRestClientV3.findById(2);
			ResponseEntityUtils.reportFindById(responseFindById);

			System.out.println(" findAll");
			try {
				ResponseEntity<Collection<Author>> responseFindAll = authorRestClientV3.findAll();
				ResponseEntityUtils.reportFindAll(responseFindAll);
			} catch (Exception ex) {
				System.out.println("  Exception: ");
				System.out.println("   " + ex.getMessage());
			}

			System.out.println(" save");
			Author author = AuthorFactory.create(333);
			ResponseEntity<Void> responseSave = authorRestClientV3.save(author);
			if(responseSave != null) {
				ResponseEntityUtils.reportSave(responseSave);
			} else {
				System.out.println("  null\n");
			}

			System.out.println(" update");
			ResponseEntity<Void> responseUpdate = authorRestClientV3.update(AuthorFactory.update(author));
			if (responseUpdate != null) {
				ResponseEntityUtils.reportUpdate(responseUpdate);
			} else {
				System.out.println("  null\n");
			}

			if(profilesActive.contains("delete")) {
				System.out.println(" deleteById");
				ResponseEntity<Void> responseDelete = authorRestClientV3.deleteById(author.getId());
				ResponseEntityUtils.reportDelete(responseDelete);
			}
		};
	}

	@Bean
	@Order(4)
	@Profile("rest-client-v4")
	CommandLineRunner reportingAuthorRestClientV4(AuthorRestClientV4 authorRestClientV4) {
		return args -> {
			System.out.println("Reporting Author REST Client V4");

			System.out.println(" findById");
			ResponseEntity<Author> responseFindById = authorRestClientV4.findById(2);
			ResponseEntityUtils.reportFindById(responseFindById);

			System.out.println(" findAll");
			try {
				ResponseEntity<Collection<Author>> responseFindAll = authorRestClientV4.findAll();
				ResponseEntityUtils.reportFindAll(responseFindAll);
			} catch (Exception ex) {
				System.out.println("  Exception: ");
				System.out.println("   " + ex.getMessage());
			}

			System.out.println(" save");
			Author author = AuthorFactory.create(444);
			ResponseEntity<Void> responseSave = authorRestClientV4.save(author);
			ResponseEntityUtils.reportSave(responseSave);

			System.out.println(" update");
			ResponseEntity<Void> responseUpdate = authorRestClientV4.update(AuthorFactory.update(author));
			ResponseEntityUtils.reportUpdate(responseUpdate);

			if(profilesActive.contains("delete")) {
				System.out.println(" deleteById");
				ResponseEntity<Void> responseDelete = authorRestClientV4.deleteById(author.getId());
				ResponseEntityUtils.reportDelete(responseDelete);
			}

			System.out.println(" findByIdStatus404");
			ResponseEntity<Author> responseFindByIdStatus404 = authorRestClientV4.findByIdStatus404(888);
			if (responseFindByIdStatus404 != null) {
				ResponseEntityUtils.reportFindById(responseFindByIdStatus404);
			} else {
				System.out.println("  null\n");
			}
		};
	}

	@Bean
	@Order(5)
	@Profile("web-client-v1")
	CommandLineRunner reportingAuthorWebClientV1(AuthorWebClientV1 authorWebClientV1) {
		return args -> {
			System.out.println("Reporting Author Web Client V1");

			System.out.println(" findById");
			Author author = authorWebClientV1.findById(2);
			System.out.println("   " + author.toString());

			System.out.println(" findAll");
			try {
				Collection<Author> authors = authorWebClientV1.findAll();
				System.out.println("   " + authors.toString());
				authors.forEach(a -> System.out.println("  " + a));
			}
			catch(Exception ex) {
				System.out.println("  Exception: ");
				System.out.println("   " + ex.getMessage());
			}

			System.out.println(" save");
			author = AuthorFactory.create(555);
			authorWebClientV1.save(author);

			System.out.println(" update");
			authorWebClientV1.update(AuthorFactory.update(author));

			if(profilesActive.contains("delete")) {
				System.out.println(" deleteById");
				authorWebClientV1.deleteById(author.getId());
			}
		};
	}

	@Bean
	@Order(6)
	@Profile("web-client-v2")
	CommandLineRunner reportingAuthorWebClientV2(AuthorWebClientV2 authorWebClientV2) {
		return args -> {
			System.out.println("Reporting Author Web Client V2");

			System.out.println(" findById");
			ResponseEntity<Author> responseFindById = authorWebClientV2.findById(2);
			ResponseEntityUtils.reportFindById(responseFindById);

			System.out.println(" findAll");
			try {
				ResponseEntity<Collection<Author>> responseFindALl = authorWebClientV2.findAll();
				ResponseEntityUtils.reportFindAll(responseFindALl);
			}
			catch(Exception ex) {
				System.out.println("  Exception: ");
				System.out.println("   " + ex.getMessage());
			}

			System.out.println(" save");
			Author author = AuthorFactory.create(666);
			ResponseEntity<Void> responseSave = authorWebClientV2.save(author);
			ResponseEntityUtils.reportSave(responseSave);

			System.out.println(" update");
			ResponseEntity<Void> responseUpdate = authorWebClientV2.update(AuthorFactory.update(author));
			ResponseEntityUtils.reportUpdate(responseUpdate);

			if(profilesActive.contains("delete")) {
				System.out.println(" deleteById");
				ResponseEntity<Void> responseDelete = authorWebClientV2.deleteById(666);
				ResponseEntityUtils.reportDelete(responseDelete);
			}

			System.out.println(" findByIdStatus404");
			ResponseEntity<Author> responseFindByIdStatus404 = authorWebClientV2.findByIdStatus404(999);
			if (responseFindByIdStatus404 != null) {
				ResponseEntityUtils.reportFindById(responseFindByIdStatus404);
			}
			else {
				System.out.println("  null\n");
			}
		};
	}

}
