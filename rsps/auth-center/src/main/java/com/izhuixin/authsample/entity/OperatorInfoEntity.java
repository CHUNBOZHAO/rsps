package com.izhuixin.authsample.entity;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * rsps_hdyy_operator_info 实体类
 * Wed May 23 18:43:46 CST 2018
 * @lry
 */ 
@Entity
public class OperatorInfoEntity {

	private int id;
	private String operator_id;
	private String user_name;
	private String user_pwd;
	private String name;
	private String tel;
	private Byte type;
	private Date create_time;
	private Date modify_time;
	private Byte status;
	private Byte age;
	private Byte sex;
	private Integer company_id;
	private String head_url;
	private String app_session_id;
	private String user_id;

	@Id
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id=id;
	}

	public String getOperator_id(){
		return operator_id;
	}

	public void setOperator_id(String operator_id){
		this.operator_id=operator_id;
	}

	public String getUser_name(){
		return user_name;
	}

	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

	public String getUser_pwd(){
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd){
		this.user_pwd=user_pwd;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel=tel;
	}

	public Byte getType(){
		return type;
	}

	public void setType(Byte type){
		this.type=type;
	}

	public Date getCreate_time(){
		return create_time;
	}

	public void setCreate_time(Date create_time){
		this.create_time=create_time;
	}

	public Date getModify_time(){
		return modify_time;
	}

	public void setModify_time(Date modify_time){
		this.modify_time=modify_time;
	}

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status){
		this.status=status;
	}

	public Byte getAge(){
		return age;
	}

	public void setAge(Byte age){
		this.age=age;
	}

	public Byte getSex(){
		return sex;
	}

	public void setSex(Byte sex){
		this.sex=sex;
	}

	public Integer getCompany_id(){
		return company_id;
	}

	public void setCompany_id(Integer company_id){
		this.company_id=company_id;
	}

	public String getHead_url(){
		return head_url;
	}

	public void setHead_url(String head_url){
		this.head_url=head_url;
	}

	public String getApp_session_id(){
		return app_session_id;
	}

	public void setApp_session_id(String app_session_id){
		this.app_session_id=app_session_id;
	}

	public String getUser_id(){
		return user_id;
	}

	public void setUser_id(String user_id){
		this.user_id=user_id;
	}

}

