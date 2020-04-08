package com.mapr.springframework.data.maprdb.functional;

import com.mapr.springframework.data.maprdb.core.MapROperations;
import com.mapr.springframework.data.maprdb.core.MapROptions;
import com.mapr.springframework.data.maprdb.core.MapRTemplate;
import com.mapr.springframework.data.maprdb.repository.config.EnableMapRRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableMapRRepository
@PropertySource("classpath:tests.properties")
public class MapRTestConfiguration {

    @Value("${database.name}")
    public String databaseName;

    @Value("${database.host}")
    public String databaseHost;

    @Value("${database.username}")
    public String databaseUsername;

    @Value("${database.password}")
    public String databasePassword;

    @Bean
    public MapROptions mapROptions() {
        MapROptions options = new MapROptions();
        options.setTableRootPath(databaseName);

        return options;
    }

    @Bean
    MapROperations maprOperations(MapROptions maprOptions) {
        return new MapRTemplate(maprOptions);
    }
}
