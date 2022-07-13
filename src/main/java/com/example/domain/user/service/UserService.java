package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.MUser;

public interface UserService {
    /*ユーザ登録*/
    public void signup(MUser user);

    /*ユーザ取得*/
    public List<MUser> getUsers(MUser user);

    /*ユーザ取得(1件)*/
    public MUser getUserOne(String userId);

    /*ユーザ更新*/
    public void updateUserOne(String userId,String password,String userName);

    /*ユーザ削除*/
    public void deleteUserOne(String userId);

}
