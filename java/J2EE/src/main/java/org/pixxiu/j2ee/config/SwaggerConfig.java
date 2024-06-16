package org.pixxiu.j2ee.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI SwaggerOpenApi() {
        return new OpenAPI()
                .info(new Info().title("大貔貅的个人博客")
                        .description("后台")
                        .version("V1.0.0"));
//                .externalDocs(new ExternalDocumentation()
//                        .description("文档")
//                        .url("https://hurh1mqccg.feishu.cn/docx/TIordwOyAohw4uxi8eRcvBGVnCg"));
    }
}
