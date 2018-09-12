package com.izhuixin.rsps.common.vo.wms;

/**
 * 司机基本信息
 */
public class DriverInfo {

    /** 司机ID */
    private String id;

    /** 司机名称 */
    private String name;

    /** 司机电话 */
    private String phone;

    /** 司机年龄 */
    private Integer age;

    /** 司机性别 */
    private Integer sex;

    /** 派货司机顺序 */
    private Integer order;

    /** 司机信息最后更新时间 */
    private String lastModify;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }
}
