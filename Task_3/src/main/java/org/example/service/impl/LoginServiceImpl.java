package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.LoginRepository;
import org.example.model.Login;
import org.example.service.LoginService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    @Override
    public List<Login> saveAll(List<Login> logins) {
        return loginRepository.saveAll(logins);
    }
}
