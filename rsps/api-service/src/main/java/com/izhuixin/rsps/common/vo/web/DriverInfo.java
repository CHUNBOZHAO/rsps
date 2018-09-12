package com.izhuixin.rsps.common.vo.web;

/**
 * 司机基本信息
 */
public class DriverInfo {

    /** 司机ID */
    private Long id;

    /** WMS 司机ID */
    private String driverId;

    /** 司机名称 */
    private String name;

    /** 司机电话 */
    private String phone;

    /** 司机年龄 */
    private Integer age;

    /** 司机性别 */
    private Integer sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
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
}
