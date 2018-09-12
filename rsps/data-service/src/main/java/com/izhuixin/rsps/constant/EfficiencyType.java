package com.izhuixin.rsps.constant;

public enum EfficiencyType {
    IDLE_RATE(1,"闲置率"),TURN_RATE(2,"周转率"),LOSE_RATE(3,"遗失率"),OVERDUE_RATE(4,"过期率");
    
    private Integer index;
    private String desc;
    EfficiencyType(Integer index,String desc){
        this.index = index;
        this.desc = desc;
    }

    //获取效率类型
    public static String getDesc(int index){
        for (EfficiencyType efficiencyType:EfficiencyType.values()) {
            if(efficiencyType.getIndex() == index){
                return efficiencyType.getDesc();
            }
        }
        return null;
    }


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
