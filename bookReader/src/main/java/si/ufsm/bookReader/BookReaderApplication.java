package si.ufsm.bookReader;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "BookReader",
				version = "1.0",
				description = "E-commerce de livros"

		)
)

@SpringBootApplication
public class BookReaderApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookReaderApplication.class, args);
	}

}
