package org.cloudfoundry.samples.music.config.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("db2-local")
public class DB2LocalDataSourceConfig extends AbstractLocalDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return createDataSource("jdbc:db2://localhost:50000/music", "com.ibm.db2.jcc.DB2Driver", "db2inst1", "daimlerdb2");
//        return createDataSource("jdbc:db2://localhost:50000/music", "com.ibm.db2.jcc.DB2Driver", "db2inst1", "bla.blub-test");
    }

}
