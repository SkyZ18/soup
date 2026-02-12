package com.skyz.api.clientLogic.dtos.responses;

import com.skyz.api.clientLogic.models.enums.Scope;
import com.skyz.api.clientLogic.models.enums.TokenType;

import java.util.Date;
import java.util.List;

public record SoupClientToken(
        String access_token,
        Date exp_at,
        List<String> groups,
        Date issued_at,
        Scope scope,
        TokenType token_type
) {}
