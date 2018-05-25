package jp.co.isms;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 起動クラス
 *
 */
@SpringBootApplication
//@ComponentScan
//@EnableAutoConfiguration
@org.springframework.boot.autoconfigure.domain.EntityScan(value = {"jp.co.isms.entities"})
//@EnableAsync
public class Application {

	/**
	 * メインメソッド
	 * @param args 実行時引数
	 */
	public static void main(String[] args) {
		System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
		System.setProperty("org.apache.catalina.connector.CoyoteAdapter.ALLOW_BACKSLASH", "true");

		try {
			SpringApplication.run(Application.class, args);
		} catch (UnsatisfiedDependencyException e) {
			InjectionPoint injectionPoint = e.getInjectionPoint();
			System.out.println(injectionPoint);
	    }
	}

}
