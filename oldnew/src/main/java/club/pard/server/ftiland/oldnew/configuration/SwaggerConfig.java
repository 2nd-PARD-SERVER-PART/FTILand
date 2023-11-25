package club.pard.server.ftiland.oldnew.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@OpenAPIDefinition(info = @Info(title = "OldNew API 명세서", description = "OldNew 앱에서 서버와 통신할 때 사용할 수 있는 request와 그로부터 받는 response에 대한 내용", version = "v1"))
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI()
    {
        return new OpenAPI().components(new Components());
    }
}
