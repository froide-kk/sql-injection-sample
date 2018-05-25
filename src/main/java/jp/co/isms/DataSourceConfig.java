package jp.co.isms;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * データソース
 *
 */
@Component
public class DataSourceConfig {

	/**
	 * Environment
	 */
	@Autowired
	private Environment env;

	/**
	 * 監視処理時間(ミリ秒)
	 */
	private final int timeBetween = 60000;


	/**
	 * @return DataSource
	 */
	@Bean
	@Primary
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.env.getProperty("isms.datasource.driver"));
		dataSource.setUrl(this.env.getProperty("isms.datasource.url"));
		dataSource.setUsername(this.env.getProperty("isms.datasource.username"));
		dataSource.setPassword(this.env.getProperty("isms.datasource.password"));
		// 最大プールサイズ
		dataSource.setMaxActive(Integer.parseInt(this.env
						.getRequiredProperty("isms.datasource.pool.max")));
		// 最少プールサイズ
		dataSource.setMinIdle(Integer.parseInt(this.env
						.getRequiredProperty("isms.datasource.pool.min")));
		// 初期プールサイズ
		dataSource.setInitialSize(Integer.parseInt(this.env
						.getRequiredProperty("isms.datasource.pool.initial")));
		// アイドルタイムアウト時間
		dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(this.env
						.getRequiredProperty("isms.datasource.pool.timeout.idle")));
		// 最大タイムアウト時間
		dataSource.setMaxWait(Integer.parseInt(this.env
						.getRequiredProperty("isms.datasource.pool.timeout.max")));
		dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetween);

		return dataSource;
	}

}
