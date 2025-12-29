package com.cbs.cinebook.service;

import org.springframework.security.oauth2.jwt.Jwt;

public interface UserSyncService {
   void  syncUser(Jwt accessToken);
}
