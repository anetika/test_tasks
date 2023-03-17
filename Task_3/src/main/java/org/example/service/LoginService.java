package org.example.service;

import org.example.model.Login;

import java.util.List;

public interface LoginService {
    List<Login> saveAll(List<Login> logins);
}
