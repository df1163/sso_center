package com.hsjc.central.config;


import com.hsjc.central.authrealm.MyAuthRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class SecurityConfig {

	@Bean
	public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator(){
		return new JavaUuidSessionIdGenerator();
	}

	/**
	 * 会话DAO
	 * @return
     */
	@Bean
	public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO(){
		EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
		enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
		enterpriseCacheSessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator());

		return enterpriseCacheSessionDAO;
	}

	/**
	 * 会话验证调度器
	 * 全局的会话信息检测扫描信息间隔30分钟
	 * @return
     */
	@Bean
	public QuartzSessionValidationScheduler quartzSessionValidationScheduler(){
		QuartzSessionValidationScheduler quartzSessionValidationScheduler = new QuartzSessionValidationScheduler();
		quartzSessionValidationScheduler.setSessionValidationInterval(1800000);
		quartzSessionValidationScheduler.setSessionManager(DefaultWebSessionManager());

		return quartzSessionValidationScheduler;
	}

	/**
	 * 会话管理器
	 * 全局的会话信息设置成30分钟,sessionValidationSchedulerEnabled参数就是是否开启扫描
	 * @return
     */
	@Bean
	public DefaultWebSessionManager DefaultWebSessionManager(){
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		defaultWebSessionManager.setGlobalSessionTimeout(1800000);
		defaultWebSessionManager.setDeleteInvalidSessions(true);
		defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
		defaultWebSessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler());
		defaultWebSessionManager.setSessionDAO(enterpriseCacheSessionDAO());

		return defaultWebSessionManager;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);

		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public MyAuthRealm myAuthRealm(ApplicationContext applicationContext) {
		MyAuthRealm myAuthRealm = new MyAuthRealm();
		myAuthRealm.setAc(applicationContext);
		myAuthRealm.setCredentialsMatcher(HashedCredentialsMatcher());
		myAuthRealm.setCachingEnabled(true);
		myAuthRealm.setAuthenticationCachingEnabled(true);
		myAuthRealm.setAuthenticationCacheName("authenticationCache");
		myAuthRealm.setAuthorizationCachingEnabled(true);
		myAuthRealm.setAuthorizationCacheName("authorizationCache");

		return myAuthRealm;
	}

	@Bean
	public EhCacheManager ehCacheManager(net.sf.ehcache.CacheManager cacheManager) {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManager(cacheManager);
		return ehCacheManager;
	}

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager cacheManager() {
		return net.sf.ehcache.CacheManager.newInstance();
	}

	@Bean
	public SimpleCookie sessionIdCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("sid");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(-1);
		return simpleCookie;
	}

	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("rm");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}

	@Bean
	public RememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		rememberMeManager.setCookie(rememberMeCookie);
		return rememberMeManager;
	}

	@Bean
	public HashedCredentialsMatcher HashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(2);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 凭证匹配器
	 * @param myAuthRealm
	 * @param ehCacheManager
	 * @param rememberMeManager
     * @return
     */
	@Bean
	public DefaultWebSecurityManager securityManager(MyAuthRealm myAuthRealm, EhCacheManager ehCacheManager, RememberMeManager rememberMeManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myAuthRealm);
		securityManager.setCacheManager(ehCacheManager);
		securityManager.setRememberMeManager(rememberMeManager);
		return securityManager;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public FormAuthenticationFilter formAuthenticationFilter(){
		FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
		formAuthenticationFilter.setUsernameParam("username");
		formAuthenticationFilter.setPasswordParam("password");
		formAuthenticationFilter.setRememberMeParam("rememberMe");
		formAuthenticationFilter.setLoginUrl("/user/login.html");
		return formAuthenticationFilter;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
		Map<String,Filter> filtersMap = new HashMap<>();
		filtersMap.put("authc", formAuthenticationFilter());

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/user/login.html");
		shiroFilterFactoryBean.setFilters(filtersMap);

		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/page/logout.html", "logout");
		filterChainDefinitionMap.put("/user/login.html", "authc");
		filterChainDefinitionMap.put("/**", "user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;

	}

	@Bean
	public LogoutFilter logout() {
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/user/login.html");
		return logoutFilter;
	}

}
