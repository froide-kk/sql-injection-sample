package jp.co.isms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.HashMap;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
				basePackages = "jp.co.isms.repositories.isms",
				entityManagerFactoryRef = "ismsEntityManager")
public class IsmsConfig {

	/**
	 * JpaVendorAdapter
	 */
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	/**
	 * データソース
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * エンティティマネージャー生成
	 *
	 * @return EntityManager
	 */
	@Bean(name = "ismsEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean gigazoEntityManager() {

		LocalContainerEntityManagerFactoryBean entityManager
						= new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(this.dataSource);
		entityManager.setJpaVendorAdapter(this.jpaVendorAdapter);
		entityManager.setPackagesToScan("jp.co.isms.entities");
		entityManager.setPersistenceUnitName("ismsPersistenceUnit");

		return entityManager;
	}

}
