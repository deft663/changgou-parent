package com.changgou.goods.vo;

import com.changgou.goods.pojo.Category;

public class CategoryVO extends Category {
    private Integer secondLevelId;

    private String secondLevelName;

    private Integer thirdLevelId;

    private String thirdLevelName;

    public Integer getSecondLevelId() {
        return secondLevelId;
    }

    public void setSecondLevelId(Integer secondLevelId) {
        this.secondLevelId = secondLevelId;
    }

    public String getSecondLevelName() {
        return secondLevelName;
    }

    public void setSecondLevelName(String secondLevelName) {
        this.secondLevelName = secondLevelName;
    }

    public Integer getThirdLevelId() {
        return thirdLevelId;
    }

    public void setThirdLevelId(Integer thirdLevelId) {
        this.thirdLevelId = thirdLevelId;
    }

    public String getThirdLevelName() {
        return thirdLevelName;
    }

    public void setThirdLevelName(String thirdLevelName) {
        this.thirdLevelName = thirdLevelName;
    }
}
