package com.eclipse.knife;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SimpleAnalysis {

    private char buf[];
    private int BUFSIZE = 2048 * 32;
    private final String str_id = "android:id=\"@+id/";
    private int n_idSize = str_id.length();
    List<String> list_id = new ArrayList<String>();
    List<SaNode> list_saNodes = new ArrayList<SaNode>(); 
    HashMap<String, String> hm_type = new HashMap<String,String>();
    public SimpleAnalysis() {
        buf = new char[BUFSIZE];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
        init();
        
    }
    public void init(){
        hm_type.put("txt", "TextView");
        hm_type.put("btn", "Button");
        hm_type.put("imgView", "ImageView");
        hm_type.put("llyt", "LinearLayout");
        hm_type.put("rlyt", "RelativeLayout");
        hm_type.put("edt", "EditText");
        hm_type.put("lv", "ListView");
        hm_type.put("imgBtn", "ImageButton");
        
        
    }
    public void parse() {
        String str = new String(buf);
        while (true) {
            if (str.isEmpty()) {
                break;
            }
            int st = str.indexOf(str_id);
            if (st != -1) {
                str = str.substring(st + n_idSize);
                list_id.add(str.substring(0, str.indexOf("\"")));
                if (st + n_idSize < str.length()) {
                    str = str.substring(st + n_idSize);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        for (String str_tmp : list_id) {
            SaNode sn = new SaNode();
            sn.str_name = str_tmp;
            String str_type = str_tmp.substring(0,str_tmp.indexOf("_"));
            sn.str_type = hm_type.get(str_type);
            list_saNodes.add(sn);
//            System.out.println(str_tmp);
        }
    }

    public void codeGen() {
        for (SaNode sn : list_saNodes) {
            System.out.println( sn.str_type + " " + sn.str_name + ";");
        }
        System.out.println("");
        for (SaNode sn : list_saNodes) {
            System.out.println("(" + sn.str_type + ") " + sn.str_name + " = ("+ sn.str_type + ") findViewById(R.id." + sn.str_name + ");");
        }
        System.out.println("");
        for (SaNode sn : list_saNodes) {
            System.out.println(sn.str_name + ".setOnClickListener(this);");
        }
        
        
    }

}
