package com.dev.market.web.security;

import com.dev.market.domain.service.MarketUserDetailsService;
import com.dev.market.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MarketUserDetailsService objMarketUserDetails;

    @Autowired
    private JwtFilterRequest jwtfilterReq;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(objMarketUserDetails);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
        web.ignoring().antMatchers("/v2/api-docs/**");
        web.ignoring().antMatchers("/swagger.json");
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //DECIRLE A SPRING SECURITY QUE TODAS LAS PETICIONES QUE TENGAN AUTHENTICATE SON PERMITIDAS, LAS DEMAS TIENEN QUE AUTENTICARSE
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/authenticate")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//le digo a SpringSecurity que no se va a manejar Sesiones sino que se manejaran JWT para las sesiones

        http.addFilterBefore(jwtfilterReq, UsernamePasswordAuthenticationFilter.class);
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
//                .anyRequest().authenticated().and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtfilterReq, UsernamePasswordAuthenticationFilter.class);
//    }


    //Bean: se le dice a Spring que use ese metodo como gestor de autenticacion de la aplicacion
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
