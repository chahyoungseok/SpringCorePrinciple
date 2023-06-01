package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @SpringBootApplication 는 내부에 @ComponentScan 이 존재하는데 그 위치가 해당 파일의 패키지 밑 전부를 스캔합니다.
 * 하지만 아래와 같이 지정한다면 app 폴더 밑에만 @ComponentScan 을 하게됩니다.
 * 강의를 진행하는 동안 AppV?Config 를 바꿔나갈거라 설정하였습니다.
 *
 * 따라서 AppV?Config 파일들을 수동으로 스프링 Bean 에 등록해야하기 때문에 @Import 사용.
 * @Import 는 클래스를 스프링 Bean 으로 등록한다.
 * 일반적으로 @Configuration 같은 설정파일을 등록할 때 사용하지만 스프링 Bean 을 등록할 때도 사용.
 *
 * **/

@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
