package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;

import form.UserDetailForm;

@Controller
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    private UserService userService;


    @Autowired
    private ModelMapper modelMapper;

    /*ユーザ詳細画面を表示*/
    @GetMapping("/detail/{userId:.+}")
    public String getUser(UserDetailForm form,Model model,@PathVariable("userId")String userId) {
        //ユーザを1件取得
        MUser user = userService.getUserOne(userId);
        user.setPassword(null);

        //MUserをformに変換
        form = modelMapper.map(user,UserDetailForm.class);

        //Modelに登録
        model.addAttribute("userDetailForm",form);

        //ユーザ詳細画面を表示
        return "user/detail";
    }

    /*ユーザ更新処理*/
    @PostMapping(value = "/detail",params = "update")
    public String updateUser(UserDetailForm form,Model model) {
        userService.updateUserOne(form.getUserId(),form.getPassword(),form.getUserName());

        //ユーザ一覧画面にリダイレクト
        return "redirect:/user/list";
    }

    /*ユーザ削除処理*/
    @PostMapping(value = "/detail",params = "delete")
    public String deleteUser(UserDetailForm form,Model model) {
        //ユーザを削除
        userService.deleteUserOne(form.getUserId());

        //ユーザ一覧にリダイレクト
        return "redirect:/user/list";
    }

}
