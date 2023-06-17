# SpringCorePrinciple

--------------------

### Advanced

**V0** : 아무런 부가기능이 없는 메인기능을 구현 <br>
**V1** : 코드들의 로그 추적기를 구현 <br>
**V2** : 로그 추적기의 싱크를 맞춤 <br>
**V3** : 싱크의 동시성 문제를 ThreadLocal을 사용해 해결하고 로그추적기를 전략 패턴화 <br>
**V4** : [템플릿 메서드 패턴](https://mirr-coding.tistory.com/41)을 이용하여 유지보수 측면을 개선 <br>
**V5** : 템플릿 메서드 패턴을 템플릿 콜백 패턴화해서 상속에 대한 자식 부모의 강결합 문제를 개선 <br>

<br><br>
### Proxy

다양한 상황에 모두 적용할 수 있는 프록시를 공부하기위한 다양한 상황 설정

#### Case
**V1** - 인터페이스와 구현 클래스 {스프링 Bean 으로 수동 등록} <br>
**V2** - 인터페이스 없는 구체 클래스 {스프링 Bean 으로 수동 등록} <br>
**V3** - 인터페이스 없는 구체 클래스 {@componentscan 으로 스프링 Bean 자동 <br>

#### Config
**V1** - [Decorator 패턴](https://mirr-coding.tistory.com/42)의 의존관계 설정 및 Case - V1, V2에 원본코드를 건들이지 않고 로그 추적기 적용 <br>
**V2** - JDK 동적 프록시 구현 및 테스트, CGLIB 구현 <br>
**V3** - ProxyFactory를 이용해 CGLIB, JDK 동적 프록시를 적용할지 개발자가 알지 못해도 편리하게 사용가능 / Advisor를 통한 적용기능 및 적용대상 명확성 확립 <br>
**V4** - 빈 후처리기를 이용한 Case - V3에도 프록시 적용 <br>
**V5** - 스프링이 제공하는 빈 후처리기를 사용해 자동으로 Advisor를 등록 및 AspectJExpressionPointcut 를 이용한 조건 삽입 <br>
**V6** - @Aspect 를 사용한 어노테이션 기반 프록시 적용 <br>

<br><br>
### AOP

 * 다양한 PointCut 지시자와 사용법
 * Order를 사용한 우선순위
 * 다양한 Advise 종류와 우선순위
 * JoinPoint 와 ProceedJoinPoint
 * 다양한 Match 조건 및 특정 지시자 주의사항
 * 프록시 내부호출문제와 그에따른 대안들
 * 스프링 AOP의 한계
 * CGLIB의 단점 및 그에따른 스프링의 해결방안

<br>

위의 내용을 코드와 설명으로 기록한 저의 [블로그](https://mirr-coding.tistory.com/43) 입니다. 

<br><br>

### MyLittleAOP

#### 목적 : 위 학습한 내용을 토대로 나만의 AOP를 만들어보자

1. 욕설 필터링 AOP

다음과 같이 욕설이라고 설정해둔 특정 단어들을 대상으로 필터링 해주는 AOP를 구현하였습니다. <br>

![욕설필터링1](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/adf4ad5a-60fa-48ac-ab3b-71061bffb626)

아래와 같은 Aspect를 만들어 Service단에서 어노테이션을 적용시켜 사용하였습니다.<br>

![욕설필터링2](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/97cc6850-ee77-41d6-895a-c7414fec5521)

욕설필터링에 사용된 알고리즘은 제가 구현해놓았던 알고리즘을 사용하였습니다. 자세한 참고사항은 [링크](https://elite-chartreuse-3ec.notion.site/d0132f0d4621400face6f3e17e337c97?pvs=4)에서 확인 하실 수 있습니다.

<br><br>

2. 암호화

로그인하면 USER, REGISTER, ADMIN 중 자신이 가진 권한을 AES로 암호화하여 토큰처럼 주는 로직을 작성하였습니다. <br>

![암호화1](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/614add25-5f9f-419f-95e7-7496d1f44f28)

해당 토큰을 가지고 요청을 보내보겠습니다. <br>

![암호화2](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/fcb51e9f-8f25-4ffb-b695-b1392b62de50)

Service 단에 다음과 같이 로직을 가지고 있기 때문에 AES를 이용한 복호화 과정이 필요합니다. <br>

![암호화4](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/75afbcb1-397c-4334-87bd-71f95540d064)

해당 AES 복호화 과정은 아래와 같이 Advise 안에 적용하여 사용하였습니다. <br>

![암호화3](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/1c1b6079-2bfd-4b2e-a9c5-b7ae6298ca65)

<br><br>

3. 조회수 최적화 (with Redis)

사용자가 조회를 할 때마다 조회 수 업데이트 쿼리를 날리는 것은 비효율적입니다.<br>
그렇기에 Write Back 패턴과 같은 Redis의 전략이 있습니다. <br>
저는 해당 패턴을 AOP화 시켜 사용할 것이고, hit_apply 요청이 오면 DB에 적용할 수 있게 구현하겠습니다. <br><br>

다음과 같이 hits 를 확인해볼 수 있습니다. <br>

![조회수 최적화](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/46ad0ab8-a6a8-4c85-8718-d78b780ba220)

하지만 아래의 로직을 본다면 hit의 증가 로직은 보이지가 않습니다. <br>

![조회수 최적화2](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/0ab48cd7-d9e3-43e3-8e6d-081ec7d304e2)

앞서 설명한 hit_apply 요청을 보내겠습니다. <br>

![조회수 최적화3](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/a6cd8640-0a2c-4f62-bd91-cb9853ef19a3)

다음과 같이 이제껏 쌓여있던 조회 수가 적용된 것을 볼 수 있습니다. <br>

![조회수 최적화4](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/5bebde08-7864-443f-912b-0f3d6e8bdcce)

다음과 같은 Advise를 통하여 Write Back 패턴을 적용해보았습니다. <br>

![조회수 최적화5](https://github.com/chahyoungseok/SpringCorePrinciple/assets/29851990/6508de0d-aabe-443b-ab43-90bb9d42338b)



