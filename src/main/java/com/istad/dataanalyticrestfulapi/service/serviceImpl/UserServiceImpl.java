package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserAccount;
import com.istad.dataanalyticrestfulapi.model.request.UserRequest;
import com.istad.dataanalyticrestfulapi.repository.UserRepository;
import com.istad.dataanalyticrestfulapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // inject repository
    UserRepository userRepository;
    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public PageInfo<User> allUsers(int page, int size, String filterName) {

        PageHelper.startPage(page, size);
        return new PageInfo<>(userRepository.allUsers(filterName));
    }

    @Override
    public List<User> findUserByName() {
        return null;
    }

    @Override
    public User findUserByID(int id) {
        return userRepository.findUserByID(id);
    }

    @Override
    public int createNewUser(UserRequest user) {
        return userRepository.createNewUser(user);
    }

    @Override
    public int updateUser(UserRequest user, int id ) {
        return userRepository.updateUser(user, id);
    }

    @Override
    public int removeUser(int id) {
        return 0;
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userRepository.getAllUserAccounts();
    }
}
