package za.co.curro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${base_package}")
    private String BASE_PACKAGE;

    // ----------------------------------------------------------------------------------------------------------

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfoMetaData())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    // ----------------------------------------------------------------------------------------------------------

    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("Jwt POC Service")
                .description("API Endpoint Decoration")
                .contact(new Contact("Dev-Team", "https://za.co.curro/", "dev-team@com.example.rain"))
                .license("Jwt POC Demo")
                .licenseUrl("http://licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }
}
