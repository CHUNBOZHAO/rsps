package com.izhuixin.authsample.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * sys_permission_user 实体类
 * Wed May 23 15:00:57 CST 2018
 * @lry
 */ 
@Entity
public class SysPermissionUserEntity {

	private int id;
	private String permission_id;
	private String user_id;
	private Date create_time;

	@Id
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id=id;
	}

	public String getPermission_id(){
		return permission_id;
	}

	public void setPermission_id(String permission_id){
		this.permission_id=permission_id;
	}

	public String getUser_id(){
		return user_id;
	}

	public void setUser_id(String user_id){
		this.user_id=user_id;
	}

	public Date getCreate_time(){
		return create_time;
	}

	public void setCreate_time(Date create_time){
		this.create_time=create_time;
	}

}

