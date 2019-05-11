package teste.configuracao.database;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfiguracaoOAuthDB {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.oauth")
    public DataSourceProperties oauthDataSource() {
        return new DataSourceProperties();
    }
}
