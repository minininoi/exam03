package org.koreait.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing //설정에서 (@EntityListeners)활성화
public class MvcConfig implements WebMvcConfigurer {
}
