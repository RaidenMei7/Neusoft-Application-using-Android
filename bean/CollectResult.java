package com.example.zyfypt_no7_406ml.bean;

public class CollectResult<T>{
    private int id;
    private String resid;
    private String userid;
    private Object cctime;
    private Object listorder;
    private String vstate;
    private T bean;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Object getCctime() {
        return cctime;
    }

    public void setCctime(Object cctime) {
        this.cctime = cctime;
    }

    public Object getListorder() {
        return listorder;
    }

    public void setListorder(Object listorder) {
        this.listorder = listorder;
    }

    public String getVstate() {
        return vstate;
    }

    public void setVstate(String vstate) {
        this.vstate = vstate;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
