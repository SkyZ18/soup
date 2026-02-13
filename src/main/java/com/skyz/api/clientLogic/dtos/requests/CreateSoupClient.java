package com.skyz.api.clientLogic.dtos.requests;

import com.skyz.api.clientLogic.models.enums.Scope;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSoupClient {
    private Scope scope;
}
