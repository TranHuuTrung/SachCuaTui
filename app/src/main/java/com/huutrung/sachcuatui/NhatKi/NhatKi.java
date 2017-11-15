package com.huutrung.sachcuatui.NhatKi;

import java.io.Serializable;

/**
 * Created by Admin on 11/7/2017.
 */

public class NhatKi implements Serializable {
   //khai bao cac thuic tinh cua 1 mau nhat ki
    private int NhatKiId;
    private String NhatKiTitle;
    private String NhatKiContent;

    public NhatKi(){

    }
    public NhatKi(String NhatKiTitle, String NhatKiContent){
       this.NhatKiTitle = NhatKiTitle;
        this.NhatKiContent = NhatKiContent;
    }
    public NhatKi(int NhatKiId, String NhatKiTitle, String NhatKiContent){
        this.NhatKiId = NhatKiId;
        this.NhatKiTitle = NhatKiTitle;
        this.NhatKiContent = NhatKiContent;
    }

    public int getNhatKiId() {
        return NhatKiId;
    }

    public void setNhatKiId(int nhatKiId) {
        NhatKiId = nhatKiId;
    }

    public String getNhatKiTitle() {
        return NhatKiTitle;
    }

    public void setNhatKiTitle(String nhatKiTitle) {
        NhatKiTitle = nhatKiTitle;
    }

    public String getNhatKiContent() {
        return NhatKiContent;
    }

    public void setNhatKiContent(String nhatKiContent) {
        NhatKiContent = nhatKiContent;
    }

    @Override
    public String toString() {
        return this.NhatKiTitle;
    }
}
