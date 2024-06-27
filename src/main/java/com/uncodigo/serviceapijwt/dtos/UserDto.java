package com.uncodigo.serviceapijwt.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uncodigo.serviceapijwt.models.Role;
import com.uncodigo.serviceapijwt.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Integer id;
    @NotBlank
    @Size(min = 4, max = 50)
    private String name;
    @NotBlank
    @Email
    private String email;
    private boolean enabled;
    private Collection<Role> roles;
    private BankAccountDto bankAccount;

    public UserDto() {
    }

    public UserDto(Integer id, String name, String email, boolean enabled, Collection<Role> roles, BankAccountDto bankAccount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
        this.bankAccount = bankAccount;
    }

    public static UserDto fromUser(User user) {
        BankAccountDto bankAccountDto = null;
        if (user.getBankAccount() != null) {
            bankAccountDto = BankAccountDto.fromBankAccount(user.getBankAccount());
        }
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isEnabled(),
                user.getRoles(),
                bankAccountDto
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public BankAccountDto getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountDto bankAccount) {
        this.bankAccount = bankAccount;
    }
}
