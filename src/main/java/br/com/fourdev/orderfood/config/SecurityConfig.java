package br.com.fourdev.orderfood.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/css/**")
		.antMatchers("/images/**")
		.antMatchers("/font/**")
		.antMatchers("/js/**")
		.antMatchers("/gs-guide-websocket/**") // Deyvid - permissão para o websocket
		.antMatchers("/mesa/verificarmesa/**") // Deyvid - permissão para chamada do aplicativo
		.antMatchers("/pedido/gerarpedido/**") // Deyvid - permissão para chamada do aplicativo
		.antMatchers("/mesa/statusmesa/**") // Deyvid - permissão para chamada do aplicativo		
		.antMatchers("/foto/**"); // Marco - permissão para acessar fotos
			
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/produto/novo").hasAnyAuthority("CADASTRAR_PRODUTO")
				.antMatchers("/usuario/novo").hasAnyAuthority("CADASTRAR_USUARIO")
				.antMatchers("/mesa/novo").hasAnyAuthority("CADASTRAR_MESA")
				.antMatchers("/mesa/status").hasAnyAuthority("LISTAR_MESA")
				.antMatchers("/pedido/abertos").hasAnyAuthority("LISTAR_PEDIDO")
				.antMatchers("/mesa/verificarmesa").hasAnyAuthority("LISTAR_MESA")
				.antMatchers("/").hasAnyAuthority("ACESSO_GERAL")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/login").permitAll()
			.and().rememberMe()
			.and()
			.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
