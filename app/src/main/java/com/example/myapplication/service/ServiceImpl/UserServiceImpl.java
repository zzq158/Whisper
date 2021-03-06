package com.example.myapplication.service.ServiceImpl;

import android.util.Log;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.dao.DaoImpl.UserDaoImpl;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.UserService;
import com.example.myapplication.util.SPHelper;

import org.litepal.LitePal;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final String TAG = "UserServiceImpl";
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public Boolean doRegister(UserInfo userInfo, String password) {

        if (userDao.IncreaseUser(userInfo, password)){
            Log.d(TAG, "doRegister: true");
            return true;
        }
        return false;
    }

    @Override
    public Boolean doLogin(LoginInfo user) {
        List<LoginInfo> login = userDao.findLoginInfo(user);
        if (login.size() > 0 && login.get(0).getPassword().equals(user.getPassword())){
            Log.d(TAG, "doLogin: successful. userID:" + user.getIdUser());
            return true;
        }
        Log.d(TAG, "doLogin: fail");
        return false;
    }

    @Override
    public List<UserInfo> findUserList() {
        List<UserInfo> userInfos = userDao.findUserList();
        Log.d(TAG, "findUserList: " + userInfos.size());
        return userInfos;
    }

    @Override
    public UserInfo findUserByID(String idUser) {

        List<UserInfo> userInfos = userDao.findUserInfoByID(idUser);
        if(userInfos.size() > 0){
            Log.d(TAG, "findUserByID: " + userInfos.get(0).getIdUser());
            return userInfos.get(0);
        }
        return null;
    }

    @Override
    public Boolean updatePassword(String idUser, String password) {
        return userDao.updatePassword(idUser, password);
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        return userDao.updateUserinfo(userInfo);
    }
}
