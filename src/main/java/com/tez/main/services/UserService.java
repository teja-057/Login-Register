package com.tez.main.services;

import com.tez.main.entities.User;

public interface UserService {
	public boolean registerUser(User user);
	public User loginUser(String email, String password);
}
