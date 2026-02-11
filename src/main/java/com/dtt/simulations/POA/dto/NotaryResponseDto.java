package com.dtt.simulations.POA.dto;

import java.util.List;

public class NotaryResponseDto {

    private List<NotaryDto> notaryDtoList;

    private List<String> scope;

    public List<NotaryDto> getNotaryDtoList() {
        return notaryDtoList;
    }

    public void setNotaryDtoList(List<NotaryDto> notaryDtoList) {
        this.notaryDtoList = notaryDtoList;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }
}
