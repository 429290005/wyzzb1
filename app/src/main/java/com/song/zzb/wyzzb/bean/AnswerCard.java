package com.song.zzb.wyzzb.bean;

import java.io.Serializable;

/**
 * Created by song on 2016/5/27.
 */
public class AnswerCard implements Serializable {
    private static final long serialVersionUID = 1L;
    public int getIsselected() {
        return isselected;
    }

    public void setIsselected(int isselected) {
        this.isselected = isselected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;
    private int isselected;

    public String getCorrectcode() {
        return correctcode;
    }

    public void setCorrectcode(String correctcode) {
        this.correctcode = correctcode;
    }

    private String correctcode; //正确的答案编码

    public String getMySelected() {
        return mySelected;
    }

    public void setMySelected(String mySelected) {
        this.mySelected = mySelected;
    }

    private String mySelected;//我选择的答案

    public int getCorrectSelected() {
        return correctSelected;
    }

    public void setCorrectSelected(int correctSelected) {
        this.correctSelected = correctSelected;
    }

    private int correctSelected;//已选择后判断为正确答案

}
