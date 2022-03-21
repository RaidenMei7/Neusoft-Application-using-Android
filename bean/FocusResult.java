package com.example.zyfypt_no7_406ml.bean;

public class FocusResult<User> {
    private String id;
    private String idolid;
    private String fwtime;
    private Object listorder;
    private String userid;
    private String vstate;
    private User bean;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdolid() {
        return idolid;
    }

    public void setIdolid(String idolid) {
        this.idolid = idolid;
    }

    public String getFwtime() {
        return fwtime;
    }

    public void setFwtime(String fwtime) {
        this.fwtime = fwtime;
    }

    public Object getListorder() {
        return listorder;
    }

    public void setListorder(Object listorder) {
        this.listorder = listorder;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVstate() {
        return vstate;
    }

    public void setVstate(String vstate) {
        this.vstate = vstate;
    }

    public User getBean() {
        return bean;
    }

    public void setBean(User bean) {
        this.bean = bean;
    }

}
