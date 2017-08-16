package com.song.zzb.wyzzb.localbean;

import org.litepal.crud.DataSupport;

/**
 * Created by song on 2016/5/8.
 */
public class LocalComChapterPaper extends DataSupport {
    private int indexID;//题号
    private String titleSubject;//标题
    private String answer;//答案
    private String optionA;//选项
    private int chapterflag;//属于哪章节



    public int getChapterflag() {
        return chapterflag;
    }

    public void setChapterflag(int chapterflag) {
        this.chapterflag = chapterflag;
    }


    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    private String core;//考点
    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    private String answerCode;//答案编码
    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public int getIndexID() {
        return indexID;
    }

    public void setIndexID(int indexID) {
        this.indexID = indexID;
    }

    public String getTitleSubject() {
        return titleSubject;
    }

    public void setTitleSubject(String titleSubject) {
        this.titleSubject = titleSubject;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(int subjectType) {
        this.subjectType = subjectType;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    private String optionB;
    private String optionC;
    private String optionD;
    private int subjectType;//题目类型

    public int getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(int questiontype) {
        this.questiontype = questiontype;
    }

    private int questiontype;//试题类型（基础、强化、冲刺、）
    private String explain;//答案解析
}

