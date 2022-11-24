package br.com.ads.automobilistico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.ads.automobilistico"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(info());
    }

    private ApiInfo info(){
        return new ApiInfo("Documentação Oficial do aplicativo Automobilistico", "Aplicação desenvolvida como trabalho final para o Jorge, denominada Automobilistico, contendo tudo que ele pediu na tarefa!", "1.0",
                "http://www.cesarefernanda.trabalhosdafaculdade.com.br",
                new Contact("Fernanda Freitas da Silva, César Hernandes Molina", "http://www.cesarefernanda.trabalhosdafaculdade.com.br",
                        "fernanda.silva152@fatec.sp.gov.br, cesar.molina@fatec.sp.gov.br" ),
                "License X", "http://www.cesarefernanda.trabalhosdafaculdade.com.br/automobilisticodojorge",
                Collections.emptyList());
    }
}
