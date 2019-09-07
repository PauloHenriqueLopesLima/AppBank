package com.neomatrix.appbank.model;

public class UserAccountResponse {

    private UserAccount userAccount;

    public UserAccountResponse(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
