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
