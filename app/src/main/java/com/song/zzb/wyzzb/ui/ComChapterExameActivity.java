//package com.song.zzb.wyzzb.ui;
//
//
//import android.app.Dialog;
//import android.content.ContentValues;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.song.zzb.wyzzb.R;
//import com.song.zzb.wyzzb.bean.AnswerCard;
//import com.song.zzb.wyzzb.bean.ComChapterPaper;
//import com.song.zzb.wyzzb.fragment.ComChapterAnswerCardFragment;
//import com.song.zzb.wyzzb.fragment.ComChapterResultCardFragment;
//import com.song.zzb.wyzzb.localbean.LocalChapterRecord;
//import com.song.zzb.wyzzb.localbean.LocalComChapterPaper;
//import com.song.zzb.wyzzb.util.ActivityUtil;
//import com.song.zzb.wyzzb.util.DeletableEditText;
//import com.song.zzb.wyzzb.util.LogUtil;
//import com.song.zzb.wyzzb.util.ToastUtil;
//
//import org.litepal.crud.DataSupport;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.bmob.v3.BmobQuery;
//import cn.bmob.v3.listener.FindListener;
//
//
///**
// * Created by song on 2016/5/8.
// */
//public class ComChapterExameActivity extends BaseActivity {
//    private CheckBox cb1, cb2, cb3, cb4;
//    private Button btn_submit, answer_btn;//复选、填空按钮
//    private TextView tv_title, tv_explain, tv_record, tv_type;
//    private DeletableEditText det_answer_input;
//    private RadioButton radioA, radioB, radioC, radioD;
//    private RadioGroup radioGroup;
//    private TextView forword_btn, next_btn, tv_submittest, tv_addwrong, answer_tv, tv_explain_btn, core_tv;//底部布局
//    private LinearLayout ly_wrong, ly_title, ly_edt, sc_ly;
//    boolean isHandIn;// 表示交卷后
//    private int Option;// 表示是随机 or 顺序 or错题
//    private int isexplain;//判断是否点了解析
//    private int curIndex = 0;//题目编号
//    private int[] myWAset = new int[1074];// 以往错题
//    public int problemLimit = 0;
//    private List<ComChapterPaper> tiKuContentList = new ArrayList<ComChapterPaper>();
//    private int[] value;
//
//    private String title;
//    private int startfrom;
//    private List<AnswerCard> answerCards = new ArrayList<AnswerCard>();
//    private List<AnswerCard> resultCards = new ArrayList<AnswerCard>();
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chapter);
//        initProgressDialog("正在加载...");
//        initView();
//        if (Option != 2) {
//            value = new int[2];
//            Bundle bundle = getIntent().getExtras();
//            value = bundle.getIntArray("value");
//            final List<LocalChapterRecord> newsList1 = DataSupport.where("chapterflag = ?", String.valueOf(value[1])).find(LocalChapterRecord.class);
//            if (newsList1.isEmpty() || newsList1.size() == 0) {
//                queryAnswer(value[0], value[1]);
////                ToastUtil.showToast(ComChapterExameActivity.this, "查询结果为空");
//            } else {
//                LogUtil.i("生命周期", "indexID=" + curIndex + "chapterflag =" + newsList1.get(0).getChapterflag());
//                if (ActivityUtil.hasNetwork(getApplication())) {
////                    ToastUtil.showToast(ComChapterExameActivity.this, "网络存在");
//                    final Dialog builder = new Dialog(ComChapterExameActivity.this, R.style.dialog);
//                    builder.setContentView(R.layout.answer_dialog);
//                    TextView confirm_btn = (TextView) builder.findViewById(R.id.dialog_sure);
//                    TextView content = (TextView) builder.findViewById(R.id.dialog_content);
//                    TextView cancel_btn = (TextView) builder.findViewById(R.id.dialog_cancle);
//                    content.setText("上次您做到第" + newsList1.get(0).getIndexID() + "题,是否重新开始？");
//                    confirm_btn.setText("是");
//                    cancel_btn.setText("否");
//                    confirm_btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            builder.dismiss();
//                            initProgressDialog("正在加载...");
//                            DataSupport.deleteAll(LocalChapterRecord.class, "chapterflag = ? ", String.valueOf(value[1]));
//                            queryAnswer(value[0], value[1]);
//
//                        }
//                    });
//
//                    cancel_btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            builder.dismiss();
//                            initProgressDialog("正在加载...");
//                            curIndex = newsList1.get(0).getIndexID();
//                            queryAnswer(value[0], value[1]);
//                            LogUtil.i("生命周期", "curIndex=" + 1);
//                            initSubject();
//
//                        }
//                    });
//                    builder.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//
//                    builder.show();
//
//
//                }
////                ToastUtil.showToast(ComChapterExameActivity.this, "网络不存在");
//
//            }
//            if (Option == 2) {
//                //   Log.i("ly_wrong",""+isexplain);
//                isexplain = 1;
//                tv_explain_btn.setTextColor(getResources().getColor(R.color.color_theme));
//                tv_explain_btn.setSelected(true);
//                ly_wrong.setVisibility(View.VISIBLE);
//            }
//            forword_btn.setOnClickListener(new View.OnClickListener() {
//                // 上一题
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
//                    //当前为第一题时，给出提示，使上一题按钮不可用
//                    if (curIndex == 0) {
//                        ToastUtil.showToast(getApplicationContext(), "当前为第一题");
//                    } else {
//                        //   Log.i("curIndex1的值是",String.valueOf(curIndex));
//                        //为假，如果没有交卷，则返回上一题
//                        curIndex--;
//                        initSubject();
//                    }
//
//                }
//            });
//
//         /*
//         * 下一题
//		 */
//            next_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
////                Log.i("tiKuContentList.size()", "" + tiKuContentList.size());
//                    if (curIndex == tiKuContentList.size() - 1) {
//                        ToastUtil.showToast(getApplicationContext(), "当前为最后一题");
//                    } else {
////                    Log.i("curIndex2的值是",String.valueOf(curIndex));
//                        curIndex++;
//                        initSubject();
////                    }
//                    }
//                }
//
//            });
//        /*
//         * 答题卡
//		 */
//            tv_submittest.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (Option == 2) {
//                        ComChapterResultCardFragment comChapterResultCardFragment = new ComChapterResultCardFragment();
//                        Bundle bundleNumber = new Bundle();
//                        bundleNumber.putSerializable("number", (Serializable) answerCards);
//                        comChapterResultCardFragment.setArguments(bundleNumber);
//                        comChapterResultCardFragment.show(getFragmentManager(), "dialog_fragment");
//                    } else {
//                        ComChapterAnswerCardFragment comChapterAnswerCardFragment = new ComChapterAnswerCardFragment();
//                        Bundle bundleNumber = new Bundle();
//                        bundleNumber.putSerializable("number", (Serializable) answerCards);
//                        comChapterAnswerCardFragment.setArguments(bundleNumber);
//                        comChapterAnswerCardFragment.show(getFragmentManager(), "dialog_fragment");
//                    }
//
//                }
//            });
//            //返回键
//            leftMenu.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View arg0) {
//                    finish();
//                }
//            });
//        /*
//         * 加入错题库
//		 */
//            tv_addwrong.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    if (Option == 2) {
//                        myWAset[curIndex] = 0;
//                        List<LocalComChapterPaper> newsList = DataSupport.where("indexID = ? and chapterflag = ?", String.valueOf(tiKuContentList.get(curIndex).getIndexID()), String.valueOf(tiKuContentList.get(curIndex).getChapterflag())).find(LocalComChapterPaper.class);
//                        if (newsList.isEmpty() || newsList.size() == 0) {
//                            saveWaset();
//                        } else {
//                            ToastUtil.showToast(getApplicationContext(), "已存在");
//                        }
//
//                    } else {
//                        myWAset[curIndex] = 1;
//                        List<LocalComChapterPaper> newsList = DataSupport.where("indexID = ? and chapterflag = ?", String.valueOf(tiKuContentList.get(curIndex).getIndexID()), String.valueOf(tiKuContentList.get(curIndex).getChapterflag())).find(LocalComChapterPaper.class);
//                        if (newsList.isEmpty()) {
//                            saveWaset();
//                        } else {
//                            ToastUtil.showToast(getApplicationContext(), "已存在");
//                        }
//                    }
//
//
//                }
//            });
//        /*
//         * 选择radio
//		 */
//            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup group, int checkedId) {
//                    // TODO Auto-generated method stub
//                    if (!isHandIn) {//没交卷的情况下
//                        if (radioA.isChecked() && !answerCards.get(curIndex).getMySelected().equals("1")) {
////                         mySelect[curIndex] = "1";
//                            answerCards.get(curIndex).setIsselected(1);
//                            answerCards.get(curIndex).setMySelected("1");
//                            next_btn.performClick();//模拟点击了下一题，是使用代码主动去调用控件的点击事件（模拟人手去触摸控件）
//                        } else if (radioB.isChecked() && !answerCards.get(curIndex).getMySelected().equals("2")) {
////                        mySelect[curIndex] = "2";
//                            answerCards.get(curIndex).setIsselected(1);
//                            answerCards.get(curIndex).setMySelected("2");
//                            next_btn.performClick();
//                        } else if (radioC.isChecked() && !answerCards.get(curIndex).getMySelected().equals("3")) {
////                        mySelect[curIndex] = "3";
//                            answerCards.get(curIndex).setIsselected(1);
//                            answerCards.get(curIndex).setMySelected("3");
//                            next_btn.performClick();
//                        } else if (radioD.isChecked() && !answerCards.get(curIndex).getMySelected().equals("4")) {
////                        mySelect[curIndex] = "4";
//                            answerCards.get(curIndex).setIsselected(1);
//                            answerCards.get(curIndex).setMySelected("4");
//                            next_btn.performClick();
//                        }
//                    }
//                }
//            });
//	     /*复选框监听事件*/
//            btn_submit.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    String result = "";  //用来存放结果
//                /*取出选中的内容，换行显示*/
//                    if (cb1.isChecked()) result += 1;
//                    if (cb2.isChecked()) result += 2;
//                    if (cb3.isChecked()) result += 3;
//                    if (cb4.isChecked()) result += 4;
//                    if (!result.equals("")) {
//                        answerCards.get(curIndex).setIsselected(1);
//                        answerCards.get(curIndex).setMySelected(result);
//                        next_btn.performClick();
//                    } else {
//                        ToastUtil.showToast(getApplicationContext(), "请选择");
//
//                    }
//
//                }
//            });
//        /*复选框监听事件*/
//            answer_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if (!det_answer_input.getText().toString().equals("")) {
//                        answerCards.get(curIndex).setIsselected(1);
//                        answerCards.get(curIndex).setMySelected(det_answer_input.getText().toString());
//                        next_btn.performClick();
//                    } else {
//                        ToastUtil.showToast(getApplicationContext(), "请填空");
//                    }
//
//                }
//            });
//
//
//        }
//    }
//    /*
// * 保存错题库;
// */
//    protected void saveWaset(){
//        LocalComChapterPaper localComChapterPaper = new LocalComChapterPaper();
//        localComChapterPaper.setIndexID(tiKuContentList.get(curIndex).getIndexID());
//        localComChapterPaper.setTitleSubject(tiKuContentList.get(curIndex).getTitleSubject());
//        localComChapterPaper.setAnswer(tiKuContentList.get(curIndex).getAnswer());
//       localComChapterPaper.setOptionA(tiKuContentList.get(curIndex).getOptionA());
//        localComChapterPaper.setOptionB(tiKuContentList.get(curIndex).getOptionB());
//        localComChapterPaper.setOptionC(tiKuContentList.get(curIndex).getOptionC());
//       localComChapterPaper.setOptionD(tiKuContentList.get(curIndex).getOptionD());
//        if(tiKuContentList.get(curIndex).getChapterflag().SIZE !=0)localComChapterPaper.setChapterflag(tiKuContentList.get(curIndex).getChapterflag());
//        if(!"".equals(tiKuContentList.get(curIndex).getCore()))localComChapterPaper.setCore(tiKuContentList.get(curIndex).getCore());
//        if(!tiKuContentList.get(curIndex).getAnswerCode().isEmpty()) localComChapterPaper.setAnswerCode(tiKuContentList.get(curIndex).getAnswerCode());
//         localComChapterPaper.setSubjectType(tiKuContentList.get(curIndex).getSubjectType());
////        localComChapterPaper.setQuestiontype(tiKuContentList.get(curIndex).getQuestiontype());
//        if(!"".equals(tiKuContentList.get(curIndex).getExplain()))localComChapterPaper.setExplain(tiKuContentList.get(curIndex).getExplain());
//
//        if (localComChapterPaper.save()) {
//            Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "收藏失败", Toast.LENGTH_SHORT).show();
//        }
//    }
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//             finish();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void initView() {
//        onNewIntent(getIntent());
//        //如果是来自答题卡
//        //我的错题
//        setUpTitle(title);
//        setUpLeftMenu(1);
//        sc_ly = (LinearLayout) findViewById(R.id.sc_ly);
//        ly_title = (LinearLayout) findViewById(R.id.ly_title);
//        ly_edt = (LinearLayout) findViewById(R.id.ly_edt);//填空题布局
//        tv_record = (TextView) findViewById(R.id.tv_record);//做题记录
//        tv_type = (TextView) findViewById(R.id.tv_type);//题目类型
//        tv_title = (TextView) findViewById(R.id.tv_title); // 题目 标题
//        tv_explain = (TextView) findViewById(R.id.tv_explain);
//        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        radioA = (RadioButton) findViewById(R.id.radioA);//单选
//        radioB = (RadioButton) findViewById(R.id.radioB);
//        radioC = (RadioButton) findViewById(R.id.radioC);
//        radioD = (RadioButton) findViewById(R.id.radioD);
//        forword_btn = (TextView) findViewById(R.id.tv_up);//上一题
//        next_btn = (TextView) findViewById(R.id.nextBtn);//下一题
//        tv_submittest = (TextView) findViewById(R.id.tv_submittest);//交卷
//        tv_explain_btn = (TextView) findViewById(R.id.tv_explain_btn);//判断用户是否点了解析
//        ly_wrong = (LinearLayout) findViewById(R.id.ly_wrong);//答案解析布局
//        tv_addwrong = (TextView) findViewById(R.id.tv_addwrong);//加入错题库
//        answer_tv = (TextView) findViewById(R.id.answer_tv);//解析，显示答案位置
//        core_tv = (TextView) findViewById(R.id.core_tv);
//        cb1 = (CheckBox) findViewById(R.id.cb1);//复选框
//        cb2 = (CheckBox) findViewById(R.id.cb2);
//        cb3 = (CheckBox) findViewById(R.id.cb3);
//        cb4 = (CheckBox) findViewById(R.id.cb4);
//        btn_submit = (Button) findViewById(R.id.btn_submit);//提交答案
//        answer_btn = (Button) findViewById(R.id.answer_btn);
//        det_answer_input = (DeletableEditText) findViewById(R.id.det_answer_input);
//        tv_explain_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isexplain == 0) {
//                    isexplain = 1;
//                    tv_explain_btn.setTextColor(getResources().getColor(R.color.color_theme));
//                    tv_explain_btn.setSelected(true);
//                    ly_wrong.setVisibility(View.VISIBLE);
//                } else {
//                    ly_wrong.setVisibility(View.GONE);
//                    tv_explain_btn.setSelected(false);
//                    tv_explain_btn.setTextColor(getResources().getColor(R.color.text_color));
//                    isexplain = 0;
//                }
//
//            }
//        });
//
//
//    }
//
//    //index 那个章节,type 哪个类型试题
//    protected void queryAnswer(int index, int type) {
//        // TODO Auto-generated method stub
//        BmobQuery<ComChapterPaper> tiKuContent = new BmobQuery<ComChapterPaper>();
//        tiKuContent.addWhereEqualTo("chapterflag", index);
//        if (type == 1) {
//            //按照试题类型 基础练习\强化练习
//            tiKuContent.addWhereEqualTo("chapterflag", index);
//            tiKuContent.addWhereEqualTo("questiontype", 2);
//           // Log.i("indexID", "" + index + "////" + 2);
//        };
//        if (type == 2) {
//            //按照试题类型 基础练习\强化练习
//            tiKuContent.addWhereEqualTo("chapterflag", index);
//            tiKuContent.addWhereEqualTo("questiontype", 4);
//          //  Log.i("indexID", "" + index + "////" + 4);
//        }
//
//      //  Log.i("indexID", "" + index + "////" + type);
//        tiKuContent.setLimit(1000);
//        //按照时间降序
//        tiKuContent.order("indexID");
//        //判断是否有缓存，该方法必须放在查询条件（如果有的话）都设置完之后再来调用才有效，就像这里一样。
//        boolean isCache = tiKuContent.hasCachedResult(this, ComChapterPaper.class);
//        if (isCache) {
//            if (ActivityUtil.hasNetwork(this)) {
//                tiKuContent.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则
//            } else {
//                //--此为举个例子，并不一定按这种方式来设置缓存策略
//                tiKuContent.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);    // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
//            }
//        } else {
//            tiKuContent.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
//        }
//        tiKuContent.findObjects(this, new FindListener<ComChapterPaper>() {
//            @Override
//            public void onSuccess(List<ComChapterPaper> data) {
//                // TODO Auto-generated method stub
//                if (data != null) {
//                    dismissDialog();
//                    tiKuContentList.addAll(data);
//                    problemLimit = tiKuContentList.size();
//                    //给数组初始化
//                    for (int i = 0; i < problemLimit; i++) {
//                        AnswerCard answerCard = new AnswerCard();
//                        answerCard.setIndex(i+1);
//                        if (Option == 2) {
//                            answerCard.setMySelected(resultCards.get(i).getMySelected());
//                        }else{
//                            answerCard.setMySelected("");
//                        }
//                        answerCard.setCorrectcode("");
//                        answerCards.add(answerCard);
//                   //     Log.i("getAnswerCode", i+"=" + tiKuContentList.get(i).getAnswerCode());
//
//                    //    Log.i("answerCards1", "" + answerCard.getIndex());
//                    }
//                    initSubject();
//
//                //    ToastUtil.showToast(getApplication(), "数量为" + tiKuContentList.size());
//                }else{
//                    dismissDialog();
//                }
//            }
//
//            @Override
//            public void onError(int errorCode, String errorString) {
//                ToastUtil.showToast(getApplication(), errorString);
//
//                // TODO Auto-generated method stub
//            }
//        });
//    }
//
//
//    private void initSubject() {
//        //1、第一次加载、上一题、下一题、交卷后的时候调用此方法；
//        //当题库数据不为空时才显示
//        if (!tiKuContentList.isEmpty()) {
//            //初始化单选框
//            sc_ly.setVisibility(View.VISIBLE);
//            ly_title.setVisibility(View.VISIBLE);
//       if(answerCards.get(curIndex).getMySelected().isEmpty()){
//           radioGroup.clearCheck();
//           cb1.setChecked(false);
//           cb2.setChecked(false);
//           cb3.setChecked(false);
//           cb4.setChecked(false);
//       }
//            tv_title.setText("\u0020" + tiKuContentList.get(curIndex).getTitleSubject());
//            tv_record.setText((curIndex+1) + "/" + tiKuContentList.size());
//            //判断是否交卷，交卷则显示答案显示答案
//            answer_tv.setText(tiKuContentList.get(curIndex).getAnswer());
//            core_tv.setText(tiKuContentList.get(curIndex).getCore());
//            tv_explain.setText(tiKuContentList.get(curIndex).getExplain());
////            if(!tiKuContentList.get(curIndex).getAnswerCode().isEmpty())
//          //  Log.i("getAnswerCode",""+tiKuContentList.get(curIndex).getAnswerCode());
//            if(!"".equals(tiKuContentList.get(curIndex).getAnswerCode()) ||tiKuContentList.get(curIndex).getAnswerCode()!=null )
//                answerCards.get(curIndex).setCorrectcode(tiKuContentList.get(curIndex).getAnswerCode());
//            //0单选，1多选，2判断，3填空，4问答
////            多选
//            if (tiKuContentList.get(curIndex).getSubjectType() == 0) {
//                // 单选择题
//                tv_type.setText("--单选题（本题1分）");
//                radioA.setText("A." + tiKuContentList.get(curIndex).getOptionA());
//                radioB.setText("B." + tiKuContentList.get(curIndex).getOptionB());
//                radioC.setText("C." + tiKuContentList.get(curIndex).getOptionC());
//                radioD.setText("D." + tiKuContentList.get(curIndex).getOptionD());
//                radioA.setVisibility(View.VISIBLE);
//                radioB.setVisibility(View.VISIBLE);
//                radioC.setVisibility(View.VISIBLE);
//                radioD.setVisibility(View.VISIBLE);
//                radioGroup.setVisibility(View.VISIBLE);
//                cb1.setVisibility(View.GONE);
//                cb3.setVisibility(View.GONE);
//                cb4.setVisibility(View.GONE);
//                cb2.setVisibility(View.GONE);
//                btn_submit.setVisibility(View.GONE);
//                ly_edt.setVisibility(View.GONE);
//            } else if (tiKuContentList.get(curIndex).getSubjectType() == 1) {
//                tv_type.setText("--多选题（本题1分）");
////                ToastUtil.showToast(getApplicationContext(), "多选题" + tiKuContentList.get(curIndex).getSubjectType());
//                cb1.setText("A." + tiKuContentList.get(curIndex).getOptionA());
//                cb2.setText("B." + tiKuContentList.get(curIndex).getOptionB());
//                cb3.setText("C." + tiKuContentList.get(curIndex).getOptionC());
//                cb4.setText("D." + tiKuContentList.get(curIndex).getOptionD());
//                cb1.setVisibility(View.VISIBLE);
//                cb2.setVisibility(View.VISIBLE);
//                cb3.setVisibility(View.VISIBLE);
//                cb4.setVisibility(View.VISIBLE);
//                btn_submit.setVisibility(View.VISIBLE);
//                radioA.setVisibility(View.GONE);
//                radioB.setVisibility(View.GONE);
//                radioC.setVisibility(View.GONE);
//                radioD.setVisibility(View.GONE);
//                radioGroup.setVisibility(View.GONE);
//                ly_edt.setVisibility(View.GONE);
//                //判断
//            } else if (tiKuContentList.get(curIndex).getSubjectType() == 2) {
//                tv_type.setText("--判断题（本题1分）");
////                ToastUtil.showToast(getApplicationContext(), "判断题" + tiKuContentList.get(curIndex).getSubjectType());
//                radioA.setText("对");
//                radioB.setText("错");
//                radioGroup.setVisibility(View.VISIBLE);
//                radioA.setVisibility(View.VISIBLE);
//                radioB.setVisibility(View.VISIBLE);
//                radioC.setVisibility(View.GONE);
//                radioD.setVisibility(View.GONE);
//                cb1.setVisibility(View.GONE);
//                cb2.setVisibility(View.GONE);
//                cb3.setVisibility(View.GONE);
//                cb4.setVisibility(View.GONE);
//                btn_submit.setVisibility(View.GONE);
//                ly_edt.setVisibility(View.GONE);
//            } else if (tiKuContentList.get(curIndex).getSubjectType() == 3) {
//                ly_edt.setVisibility(View.VISIBLE);
//                tv_type.setText("--填空题（本题1分）");
//                radioA.setVisibility(View.GONE);
//                radioB.setVisibility(View.GONE);
//                radioC.setVisibility(View.GONE);
//                radioD.setVisibility(View.GONE);
//                radioGroup.setVisibility(View.GONE);
//                cb1.setVisibility(View.GONE);
//                cb2.setVisibility(View.GONE);
//                cb3.setVisibility(View.GONE);
//                cb4.setVisibility(View.GONE);
//                btn_submit.setVisibility(View.GONE);
//                // 填空题
//             //   ToastUtil.showToast(getApplicationContext(), "填空题" + tiKuContentList.get(curIndex).getSubjectType());
//            }
//            //初始化已做的题目
////            Log.i("mySelect[curIndex]的值是",""+mySelect[curIndex]);
//          //  Log.i("curIndex6的值是",String.valueOf(curIndex)+"已执行");
//            switch (answerCards.get(curIndex).getMySelected()) {
//
//                case "1":
//                    radioA.setChecked(true);
//                    break;
//                case "2":
//                    radioB.setChecked(true);
//
//                    break;
//                case "3":
//                    radioC.setChecked(true);
//
//                    break;
//                case "4":
//                    radioD.setChecked(true);
//
//                    break;
//                case "12":
//                    cb1.setChecked(true);
//                    cb2.setChecked(true);
//                    cb3.setChecked(false);
//                    cb4.setChecked(false);
//
//                    break;
//                case "13":
//                    cb1.setChecked(true);
//                    cb3.setChecked(true);
//                    cb2.setChecked(false);
//                    cb4.setChecked(false);
//
//                    break;
//                case "14":
//                    cb1.setChecked(true);
//                    cb4.setChecked(true);
//                    cb3.setChecked(false);
//                    cb2.setChecked(false);
//
//                    break;
//                case "1234":
//                    cb1.setChecked(true);
//                    cb2.setChecked(true);
//                    cb3.setChecked(true);
//                    cb4.setChecked(true);
//
//                    break;
//                case "123":
//                    cb1.setChecked(true);
//                    cb2.setChecked(true);
//                    cb3.setChecked(true);
//                    cb4.setChecked(false);
//
//                    break;
//                case "124":
//                    cb1.setChecked(true);
//                    cb2.setChecked(true);
//                    cb3.setChecked(false);
//                    cb4.setChecked(true);
//
//                    break;
//                case "134":
//                    cb1.setChecked(true);
//                    cb3.setChecked(true);
//                    cb2.setChecked(false);
//                    cb4.setChecked(true);
//
//                    break;
//                case "24":
//                    cb2.setChecked(true);
//                    cb1.setChecked(false);
//                    cb3.setChecked(false);
//                    cb4.setChecked(true);
//
//                    break;
//                case "23":
//                    cb2.setChecked(true);
//                    cb3.setChecked(true);
//                    cb1.setChecked(false);
//                    cb4.setChecked(false);
//
//                    break;
//                case "34":
//                    cb4.setChecked(true);
//                    cb3.setChecked(true);
//                    cb1.setChecked(false);
//                    cb2.setChecked(false);
//                    break;
//                default:
//                    det_answer_input.setText(answerCards.get(curIndex).getMySelected());
//                    break;
//            }
//        } else {
//        //    Log.i("curIndex5的值是",String.valueOf(curIndex));
//            //数据没加载好的时显示的内容
//            ly_title.setVisibility(View.GONE);
//            tv_title.setText("单项选择题、多项选择题、填空题、判断题");
//            radioA.setVisibility(View.GONE);
//            radioB.setVisibility(View.GONE);
//            radioC.setVisibility(View.GONE);
//            radioD.setVisibility(View.GONE);
//            radioGroup.setVisibility(View.GONE);
//            cb1.setVisibility(View.GONE);
//            cb2.setVisibility(View.GONE);
//            cb3.setVisibility(View.GONE);
//            cb4.setVisibility(View.GONE);
//
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        saveRecord();
//    }
//    protected void saveRecord(){
//        if(curIndex!=0) {
//            final List<LocalChapterRecord> newsList1 = DataSupport.where("chapterflag = ?", String.valueOf(value[1])).find(LocalChapterRecord.class);
//            if (newsList1.isEmpty() || newsList1.size() == 0) {
//                LocalChapterRecord localRealRecord = new LocalChapterRecord();
//                if (!tiKuContentList.isEmpty()) {
//                    localRealRecord.setIndexID(curIndex);
//                    localRealRecord.setChapterflag(tiKuContentList.get(curIndex).getChapterflag());
//                }
//                if (localRealRecord.save()) {
//                  //  Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
////               LogUtil.i("生命周期",+tiKuContentList.get(curIndex).getIndexID()+"BlongChapter="+tiKuContentList.get(curIndex).getBlongChapter());
//                } else {
//                  //  Toast.makeText(getApplicationContext(), "保存失败", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                LogUtil.i("生命周期", "" + newsList1.get(0).getIndexID());
//                ContentValues values = new ContentValues();
//                values.put("IndexID", newsList1.get(0).getIndexID());
//                DataSupport.updateAll(LocalChapterRecord.class, values, "chapterflag = ?", String.valueOf(value[1]));
//            }
//
//        }
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        initSubject();
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        setIntent(intent);
//        title = getIntent().getExtras().getString("title");
//        Option = getIntent().getExtras().getInt("option");
//        curIndex = getIntent().getExtras().getInt("startfrom");
//        resultCards=(List<AnswerCard>) getIntent().getExtras().getSerializable("answerCards");
//
//
////        answerCards = ;
////        for (int i = answerCards.size()-1; i >= 0; i--) {
////             AnswerCard answerCard =new AnswerCard();
////            //将正确答案存入testAnswer
//////            if(!number.get(i).getCorrectcode().equals("")){
////            if(!answerCards.get(i).getMySelected().equals("")){
////
////                    //答对的情况
////                answerCard.setIsselected(1);
////
////            }else{
////                //答案为空的情况
////                answerCard.setIsselected(0);
////            }
////        }
////        answerCards.add(answerCard);
//
//       // Log.i("onNewIntent", title + "//" + Option + "////" + curIndex);
//
////        }
//    }
//
//
//}
//
