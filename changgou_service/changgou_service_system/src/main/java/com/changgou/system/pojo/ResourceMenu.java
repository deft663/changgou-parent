package com.changgou.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:shenkunlin
 * @Description:ResourceMenu构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_resource_menu")
public class ResourceMenu implements Serializable{

	@Id
    @Column(name = "resource_id")
	private Integer resourceId;//

    @Column(name = "menu_id")
	private String menuId;//



	//get方法
	public Integer getResourceId() {
		return resourceId;
	}

	//set方法
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	//get方法
	public String getMenuId() {
		return menuId;
	}

	//set方法
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


}
