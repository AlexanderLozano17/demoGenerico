package com.demo.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private DataSource dataSource = null;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Configuración de las reglas de seguridad HTTP
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Habilitar CSRF solo si es necesario en tu aplicación, en aplicaciones sin estado suele ser innecesario
    	http.csrf(csrf -> csrf.disable()) // Deshabilitar CSRF si no es necesario (para aplicaciones REST)
        .authorizeHttpRequests(requests -> requests
            .requestMatchers(HttpMethod.GET, "/public/**").permitAll()  // Acceso público a ciertas rutas
            .requestMatchers("/api/admin/**").hasRole("ADMIN")  // Rutas protegidas para administradores
            .requestMatchers("/admin/**").hasRole("ADMIN")  // Rutas protegidas para administradores
            .requestMatchers("/api/user/**").hasRole("USER")  // Rutas protegidas para usuarios
            .requestMatchers("/user/**").hasRole("USER")  // Rutas protegidas para usuarios
            .anyRequest().authenticated())  // Requiere autenticación para cualquier otra solicitud

        // Configuración del login
        .formLogin(login -> login
            .loginPage("/login")  // Página de login personalizada
            .loginProcessingUrl("/login")  // URL de procesamiento de login
            .permitAll())  // Permitir acceso sin autenticación

        // Configuración del logout
        .logout(logout -> logout
            .logoutUrl("/logout")  // URL para logout
            .invalidateHttpSession(true)  // Invalidar sesión al hacer logout
            .permitAll())  // Permitir logout sin autenticación

        // Configuración de manejo de excepciones (página de acceso denegado)
        .exceptionHandling(handling -> handling
            .accessDeniedPage("/access-denied"));  // Página de error de acceso denegado

    	return http.build();  // Construir y retornar la configuración
    }


    /**
     * Definir un PasswordEncoder para la codificación de contraseñas
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Definir el AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    /**
     * Configurar el UserDetailsService para la gestión de usuarios desde una base de datos
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    /**
     * Configurar el DaoAuthenticationProvider para la autenticación de usuarios
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}