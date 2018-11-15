package com.btb.service;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.User;

public interface UserService {

	BtbResult checkData(String property,int type);

	BtbResult insertUser(User user);

	BtbResult findUser(User user);

	EasyUIDataGridResult findUserList(int page, int rows, String key);

}
