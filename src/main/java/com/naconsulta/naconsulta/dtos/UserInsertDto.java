package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.services.validations.UserInsertValid;

import java.io.Serializable;
@UserInsertValid
public class UserInsertDto extends UserFormDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String password;

    public UserInsertDto() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
