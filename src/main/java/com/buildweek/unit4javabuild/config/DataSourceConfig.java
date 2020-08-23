//package com.buildweek.unit4javabuild.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//
//import javax.sql.DataSource;
//
//public class DataSourceConfig
//{
//    @Value("${local.run.db.H2")
//    private String dbvalue;
//
//    @Value("${spring.datasource.url:}")
//    private String dbURL;
//
//    @Bean
//    public DataSource dataSource()
//    {
//        if (dbvalue.equalsIgnoreCase("POSTGRESQL"))
//        {
//            // assumes heroku
//            HikariConfig config = new HikariConfig();
//            config.setDriverClassName("org.postgresql.Driver");
//            config.setJdbcUrl(dbURL);
//            return new HikariDataSource(config);
//        } else
//        {
//            // assume h2
//            String myUrlString = "jdbc:h2:mem:testdb";
//            String myDriverClass = "org.h2.Driver";
//            String myDBUser = "sa";
//            String myDBPassword = "";
//
//            return DataSourceBuilder.create()
//                    .username(myDBUser)
//                    .password(myDBPassword)
//                    .url(myUrlString)
//                    .driverClassName(myDriverClass)
//                    .build();
//        }
//    }
//}
