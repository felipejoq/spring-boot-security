package com.uncodigo.serviceapijwt.dtos;

import com.uncodigo.serviceapijwt.models.BankAccount;
import com.uncodigo.serviceapijwt.models.User;

public class UserBankAccountDto {

    private UserDto user;
    private BankAccountDto bankAccount;

    public UserBankAccountDto(UserDto user, BankAccountDto bankAccount) {
        this.user = user;
        this.bankAccount = bankAccount;
    }

    public static UserBankAccountDto fromUserAndBankAccount(User user, BankAccount bankAccount) {
        UserDto userDto = UserDto.fromUser(user);
        BankAccountDto bankAccountDto = BankAccountDto.fromBankAccount(bankAccount);
        return new UserBankAccountDto(userDto, bankAccountDto);
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public BankAccountDto getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountDto bankAccount) {
        this.bankAccount = bankAccount;
    }
}
