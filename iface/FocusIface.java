package com.example.zyfypt_no7_406ml.iface;

public interface FocusIface {
    void focus(String mod,int idolid,String Sessionid,FocusListener listener);
    void unfocus(String mod,int idolid,String Sessionid,FocusListener listener);
    void exist(String mod,int idolid,String Sessionid,FocusListener listener);
}
