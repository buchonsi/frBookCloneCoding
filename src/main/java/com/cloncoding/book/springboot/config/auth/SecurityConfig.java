package com.cloncoding.book.springboot.config.auth;

import com.cloncoding.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity      //Spring security 설정들을 활성화시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                .authorizeRequests()    //url별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests가 선언되어야만 antMatchers옵션을 사용할 수 있다.
                //antMatchers : 권한 관리 대상을 지정하는 옵션 / url,http 메소드별로 관리가 가능하다 
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile" ).permitAll() //permitAll() : 전체 열람 권한을 준다.
                .antMatchers("/api/v1/**" ).hasRole(Role.USER.name())   //USER 권한을 가진 사람만 가능하도록 함
                //anyRequest() : 설정된 값들 이외의 나머지 URL들을 나타낸다
                //authenticated() : 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 한다. / 인증된 사용자는 로그인한 사용자들을 말한다.
                .anyRequest().authenticated()
                .and()
                //.logout().logoutSuccessUrl("/") : 로그아웃 기능에 대한 여러 설정의 진입점이다. / 로그아웃 성공시 / 주소로 이동한다.
                .logout().logoutSuccessUrl("/")
                .and()
                //oauth2Login() : OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당한다.
                .oauth2Login()
                //.userInfoEndpoint() : OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당한다.
                .userInfoEndpoint()
                //userService : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
                //리소스 서버(소셜 서비스들) 에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
                .userService(customOAuth2UserService);
    }
}
