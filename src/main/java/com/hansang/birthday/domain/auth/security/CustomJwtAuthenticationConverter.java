package com.hansang.birthday.domain.auth.security;


import com.hansang.birthday.domain.auth.userdetail.CustomUserDetailService;
import com.hansang.birthday.domain.auth.userdetail.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final CustomUserDetailService userDetailsService;
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String role = jwt.getClaimAsString("role");
        if("USER".equals(role)){
            String userId = jwt.getSubject();
            CustomUserDetails userDetails =
                    (CustomUserDetails) userDetailsService.loadUserByUsername(userId);
            return new UsernamePasswordAuthenticationToken(
                    userDetails,
                    jwt,
                    userDetails.getAuthorities()
            );

        } else if("GUEST".equals(role)){ //GUEST → DB 조회 안 하고 Jwt 그대로 Authentication에 담음
            return new org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken(
                    jwt,
                    List.of(new SimpleGrantedAuthority("ROLE_GUEST"))
            );
        }
        else{
            return new org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken(
                    jwt,
                    List.of()
            );
        }

    }
}
