package moneyhero;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class MoneyheroApplication {
	
  @Value("${db.username}")
  private String user;

  @Value("${db.password}")
  private String password;

  @Value("${db.url}")
  private String dataSourceUrl;

  @Value("${db.driver}")
  private String driverClassName;

  @Value("${db.connectionTimeoutMs}")
  private int connectionTimeoutMs;

  @Value("${db.connectionMaxLifeTimeMs}")
  private int connectionMaxLifetimeMs;


  // Data Source configuration
  @Bean
  public DataSource primaryDataSource() {
      HikariConfig hc = new HikariConfig();
      hc.setDriverClassName(driverClassName);
      hc.setJdbcUrl(dataSourceUrl);
      hc.setUsername(user);
      hc.setPassword(password);
      hc.setConnectionTestQuery("select 1 from dual");
      hc.setConnectionTimeout(connectionTimeoutMs);
      hc.setMaxLifetime(connectionMaxLifetimeMs);

      HikariDataSource ds = new HikariDataSource(hc);
      return ds;
  }

	public static void main(String[] args) {
		SpringApplication.run(MoneyheroApplication.class, args);
	}
}
