package com.ivy.vueflow.parser.enums.core;

public class DictItemBean {

    public static DictItemBean of(String value, String label) {
        final DictItemBean dictItemBean = new DictItemBean();
        dictItemBean.setValue(value);
        dictItemBean.setLabel(label);
        return dictItemBean;
    }

    public static DictItemBean of(String value, String label,String reserve) {
        final DictItemBean dictItemBean = new DictItemBean();
        dictItemBean.setValue(value);
        dictItemBean.setLabel(label);
        dictItemBean.setReserve(reserve);
        return dictItemBean;
    }

    private String value;

    private String label;

    private String reserve;

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
