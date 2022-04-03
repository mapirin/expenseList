package com.example.persistence;

public interface ExpenseUserMapper {

	public void userInfoTableInsertData(String userName, String password);

	public Integer userInfoTableSelectUserData(String userName, String password);

	public Integer userInfoTableUserExistCheck(String userName);
}
