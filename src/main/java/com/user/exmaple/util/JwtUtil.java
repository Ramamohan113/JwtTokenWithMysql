package com.user.exmaple.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;

	// 1.GENERATE THE TOKEN
	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("ramamohan")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS256,secret.getBytes())
				.compact();
	}
	
	//2.READ THE CLAIMSF
	public Claims getClaims(String token )
	{
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	//3.READ EXP DATE
	public Date getExpdate(String token)
	{
		Date expiration = getClaims(token).getExpiration();
		return expiration;
	}

	// READ SUBJECT OR USERNAME
	public String getUserName(String token)
	{
		String subject = getClaims(token).getSubject();
		return subject;
	}
	
	// VALIDATE THE EXPDATE
	public boolean isTokenExpaire(String token)
	{
		Date expdate = getExpdate(token);
		return expdate.before(new Date(System.currentTimeMillis()));
	}
	
	// VALIDATE THE USERNAME IN TOKEN AND DATABASE ,EXPDATE
	
	public boolean validateToken(String token,String username)
	{
		String userName2 = getUserName(token);
		return (username.equals(userName2) && !isTokenExpaire(token));
	}
}
