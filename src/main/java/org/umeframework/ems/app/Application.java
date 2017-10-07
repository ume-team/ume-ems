package org.umeframework.ems.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.umeframework.ems.app.appconfig.AppAutoConfiguration;

/**
 * Application
 * 
 * @author Yue MA
 */
// @ImportResource("classpath:DoraAutoConfiguration.xml")
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration.class,
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class })
@Import({
        // 启动UME框架的默认配置
        org.umeframework.dora.appconfig.AutoConfiguration.class,
        // 启动当前项目的自定义配置
        AppAutoConfiguration.class })
public class Application {

	/**
	 * Start Application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SpringApplication.run(Application.class, args);
		} catch (Throwable e) {
			if (!e.getClass().getName().startsWith("org.springframework.boot.devtools")) {
				e.printStackTrace();
			}
		}

	}
}
