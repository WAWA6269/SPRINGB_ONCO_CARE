package microservice.patient.config;

//import com.zaxalladatasource.jdbc.HikariDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DatabaseConfig {

    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource;
        dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://sql.freedb.tech:3306/freedb_oncocare?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("freedb_wawa6269");
        dataSource.setPassword("QC?ZE8Mh@?%fFpp");
        dataSource.setConnectionTimeout(30000);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("microservice.patient.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        // Hibernate properties
        java.util.Properties properties = new java.util.Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.naming.physical-strategy",
                "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.setProperty("hibernate.jdbc.time_zone", "UTC");

        emf.setJpaProperties(properties);
        return emf;
    }
}