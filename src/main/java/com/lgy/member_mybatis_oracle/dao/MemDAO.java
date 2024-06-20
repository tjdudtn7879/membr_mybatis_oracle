package com.lgy.member_mybatis_oracle.dao;

import java.util.ArrayList;

import com.lgy.member_mybatis_oracle.dto.MemDTO;



public interface MemDAO {

	public ArrayList<MemDTO> loginYn(String id, String pw);
	public void write(String mem_uid, String mem_pwd, String mem_name);
}
