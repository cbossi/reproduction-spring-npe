package org.sample.demo

import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
internal class SecurityConfig(private val authenticationFilter: AlwaysAuthenticatedFilter) : WebSecurityConfigurerAdapter() {

  override fun configure(http: HttpSecurity) {
    http
        .authorizeRequests().anyRequest().permitAll()
        .and().addFilterAt(authenticationFilter, BasicAuthenticationFilter::class.java);
  }

}

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
internal class AlwaysAuthenticatedFilter : OncePerRequestFilter() {

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    SecurityContextHolder.getContext().authentication = PreAuthenticatedAuthenticationToken("user", "username", emptyList())
    filterChain.doFilter(request, response)
  }
}
