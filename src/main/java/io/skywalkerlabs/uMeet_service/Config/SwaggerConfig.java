package io.skywalkerlabs.uMeet_service.Config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@OpenAPIDefinition(
        info = @Info(
                title = "uMeet API Service",
                version = "0.0.1",
                description = "Central API service uMeet client side."
        )
)
@Configuration
public class SwaggerConfig {

        private static final String SECURITY_SCHEME_NAME = "BearerAuth";
        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));

        }
}

/*


@Configuration
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("uMeet API")
                        .version("1.0")
                        .description("API for uMeet application"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
    }
}




 */

