package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.spring.security.MemberUserDetailsService;

@Configuration // WebConfig.java에서 SecurityConfig를 읽어오기 위한 어노테이션
@EnableWebSecurity // web에서 Security를 쓰겠다
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Spring Security 환경설정하는 클래스
	// WebSecurityConfigurerAdapter
	// 요청에 대한 보안 설정을 해주는 클래스
	
	@Bean // 우리가 만들어 놓은 MemberUserDetailsService 메모르리ㅗ 올려 사용하겠다
	public UserDetailsService memberUserDetailsService() {
		return new MemberUserDetailsService();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 내가 만든 MeberUserDetailsService와 
		// 암호화 및 복호화를 해주는 패스워드 인코더를
		// Spring Security에 등록하는 메서드
		
		auth.userDetailsService(memberUserDetailsService()).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 요청에 대한 보안 설정하는 곳,n 
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		// 클라이언트가 요청을 했을 때 권한 설정
		// 회원인증부분
		http	
			.authorizeRequests() 	/* 요청에 따른 권한을 처리하겠다 */
				.antMatchers("/")	/* 어떠한 경로로 왔을 때 권한 처리를 할 것인지 */
					.permitAll()	/* 누구나 접근 가능하게끔 전체권한 */
						.and()		/* 권한을 또 추가하겠다 */
					.formLogin()	/* 로그인 보안기능 추가 */
						.loginPage("/loginForm.do")	/* Spring Security에서 제공하는 로그인 폼이 아닌 우리가 만든 로그인 폼 사용하겠다 */
						.loginProcessingUrl("/login.do") 	/* 해당 url로 요청이 들어왔을때 Spring Security 자체 로그인 기능 수행하겠다 */
						.permitAll()	/* 누구나 로그인은 사용해야하기 때문에 권한은 모두 준다 */
						.and()		/* 권한 추가 */
					.logout()		/* 로그아웃 기능 */
						.invalidateHttpSession(true) /* 세션을 만료시키겠다. Spring Security가 알아서 */
						.logoutSuccessUrl("/")		 /* 로그아웃 성공 이후 이동할 url 작성 */
						.and()
					.exceptionHandling().accessDeniedPage("/access-denied"); /* 로그인을 안하고 특정페이지에 접근하려고 할때 특정 url로 요청하겠다 */
						
		
	}
	
	
	@Bean // 패스워드 인코딩 기능을 메모리에 올리기
	public PasswordEncoder passwordEncoder() {
		// 비밀번호 암호화 메서드
		
		return new BCryptPasswordEncoder();
		
	}
	
	
	
	
	
	
}
