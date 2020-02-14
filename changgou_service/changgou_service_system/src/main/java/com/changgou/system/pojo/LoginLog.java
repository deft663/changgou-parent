package com.changgou.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:shenkunlin
 * @Description:LoginLog构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_login_log")
public class LoginLog implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

    @Column(name = "login_name")
	private String loginName;//

    @Column(name = "ip")
	private String ip;//

    @Column(name = "browser_name")
	private String browserName;//

    @Column(name = "location")
	private String location;//地区

    @Column(name = "login_time")
	private Date loginTime;//登录时间



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getLoginName() {
		return loginName;
	}

	//set方法
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	//get方法
	public String getIp() {
		return ip;
	}

	//set方法
	public void setIp(String ip) {
		this.ip = ip;
	}
	//get方法
	public String getBrowserName() {
		return browserName;
	}

	//set方法
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	//get方法
	public String getLocation() {
		return location;
	}

	//set方法
	public void setLocation(String location) {
		this.location = location;
	}
	//get方法
	public Date getLoginTime() {
		return loginTime;
	}

	//set方法
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}


}
