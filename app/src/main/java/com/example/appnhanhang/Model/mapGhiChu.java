package com.example.appnhanhang.Model;

import java.util.HashMap;
import java.util.Map;

public class mapGhiChu {

    Map<Integer,String> map;

    public mapGhiChu() {
        super();
        map = new HashMap<Integer, String>();
        map.put(1,"ít đường");
        map.put(2,"không đá");
        map.put(3,"ít sữa");
        map.put(4,"nhiều sữa");
        map.put(5,"không đường");
        map.put(6,"muối");
        map.put(7,"nóng");
        map.put(8,"lạnh");
        map.put(9,"ngọt");
    }

    public String getValue(int key)
    {
        return map.get(key);
    }

}
