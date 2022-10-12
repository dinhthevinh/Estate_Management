package com.laptrinhjavaweb.config;

//@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

	/*
	 * @Bean AuditorAware<String> auditorProvider() { return new AuditorAwareImpl();
	 * }
	 */
	/*
	 * public class AuditorAwareImpl implements AuditorAware<String> {
	 * 
	 * @Override public Optional<String> getCurrentAuditor() { Authentication
	 * authentication = SecurityContextHolder.getContext().getAuthentication(); if
	 * (authentication == null || !authentication.isAuthenticated()) { return null;
	 * } return Optional.of(authentication.getName()); } }
	 */
}
