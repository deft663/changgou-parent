package com.changgou.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:shenkunlin
 * @Description:Resource构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_resource")
public class Resource implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

    @Column(name = "res_key")
	private String resKey;//

    @Column(name = "res_name")
	private String resName;//

    @Column(name = "parent_id")
	private Integer parentId;//



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getResKey() {
		return resKey;
	}

	//set方法
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}
	//get方法
	public String getResName() {
		return resName;
	}

	//set方法
	public void setResName(String resName) {
		this.resName = resName;
	}
	//get方法
	public Integer getParentId() {
		return parentId;
	}

	//set方法
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


}
