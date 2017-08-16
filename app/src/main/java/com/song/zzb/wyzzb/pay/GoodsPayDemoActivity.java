package com.song.zzb.wyzzb.pay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.bean.VipRecord;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
/**
 * Created by song on 2016/9/7.
 */
public class GoodsPayDemoActivity extends FragmentActivity {
    private TextView discount_price,course_price,diamond_amount,pay;
    private ImageView actionbar_left_menu;
    private EditText recipient,phone,address,remark,qq;
    private String[] value = new String[2];
    private double dd;
    SharedPreferences  var3;
    // 商户PID
    public static final String PARTNER = "2088502275420434";
    // 商户收款账号
    public static final String SELLER = "429290005@qq.com";
    // 商户私钥，pkcs8格式
//    private CommunitySDK mCommSDK =null;
    public static final String RSA_PRIVATE ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALFiLqztzrlOFPYk2bK31g8lSblM2rDKeDntdzpCsKROeBzu29WxUA5OjV7nBL1l5uj4ARnMaRYSFdPtrgg92AkrjME5mWhHBMezhi5lbbTnZkxWs3oZLIqrbWW2LNNIw0ISVY9XrlDExdrLHjz01DOalwF6FM68q1gB28HWsg7PAgMBAAECgYBBU69s8IfZpvABVsD2GM409b6ZB7ziry5n5xSp2DXFzUBfJ3i+0nZxTmyyLtKIDidkVTHSln19K1vumPInBt+wo2qr4us04AaSaMsr/PeZ6KXIvb/pD9hqWYhyhjnynciaZ1QTSeTBDDv2zxZRtYhEEmzSPRsjZfVkSgKXX6FPwQJBAObXEH5ia0YEA/6aWZakImBn1ncP40RULOCYbuwA0WgLvOHd7Q0Eu0s+ITa1hP5lARU3DwadUXrc81AL2XJtEmsCQQDEt5CDTn6fz3B5IDgrJ1inwBRBd5A3LG6Si4YI6lKTTBhTBLdFkiImUlIG/YqcTNCoUxW6r5JJ8ZVSR7RwOfYtAkEAlCBRPyhcedM91PSqpFWykXZQUpppVYFGQuDN3LcxqLhPfghwgrgUj1XQ7lBQhQW/SiPIipAMpvB9WVIhOKHw/QJANERltE213TZbtwM1iVnB94hUmXRwMAixNGS370R7PkHYsK4vF+AVDMOQoeTZ4F+UjTTOMDUYFGeI+jytF6h+3QJAa+cqzV/GYreZUdpkyTiMLnLu0LJ/GySrHZy8PW9YFyhVDMX2M3MxuXkMOkWD5JZXL3qq46p9sZsAagvp7e5YIw==";

    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxYi6s7c65ThT2JNmyt9YPJUm5TNqwyng57Xc6QrCkTngc7tvVsVAOTo1e5wS9Zebo+AEZzGkWEhXT7a4IPdgJK4zBOZloRwTHs4YuZW2052ZMVrN6GSyKq21ltizTSMNCElWPV65QxMXayx489NQzmpcBehTOvKtYAdvB1rIOzwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        VipRecord p2 = new VipRecord();
                        p2.setNickname(var3.getString("username",""));//用户昵称
                        p2.setPrice(diamond_amount.getText().toString());//最终售价
                        p2.setSystemflag(qq.getText().toString());//用户唯一标识
                        p2.setGoodsds(value[1]);//商品描述
                        p2.setPhone(phone.getText().toString());//手机号
                        p2.setAddress(address.getText().toString());//地址
                        p2.setRemark(remark.getText().toString());//备注
                        p2.setDiscount(discount_price.getText().toString());//折扣优惠
                        p2.setName(recipient.getText().toString());//真实姓名
                        p2.setInitialprice(value[0]);//原价
                        p2.save(GoodsPayDemoActivity.this);
                        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String sendTime = format.format(new Date());
                        BmobSMS.requestSMS(getApplication(), phone.getText().toString(), "斑马在线课堂：您已成功购买"+value[1]+"课程，请保持手机畅通，客服人员会在12小时之内联系您或者您直接联系客服人员扣扣！",sendTime,new RequestSMSCodeListener() {

                                            @Override
                                            public void done(Integer smsId,BmobException ex) {
                                                // TODO Auto-generated method stub
                                                if(ex==null){//
                                                    Log.i("bmob","短信发送成功，短信id："+smsId);//用于查询本次短信发送详情
                                                }else{
                                                    Log.i("bmob","errorCode = "+ex.getErrorCode()+",errorMsg = "+ex.getLocalizedMessage());
                                                }
                                            }
                                        });
                                        //ToastUtil.showToast(getApplication(),"您的积分已加200分，当前积分为"+pointOPResponse.cur_point);
                                        Toast.makeText(GoodsPayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                    } else {
//                        // 判断resultStatus 为非"9000"则代表可能支付失败
//                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(GoodsPayDemoActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(GoodsPayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_confirm);
//        mCommSDK = CommunityFactory.getCommSDK(getApplication());
        actionbar_left_menu=(ImageView)findViewById(R.id.actionbar_left_menu);
        recipient =(EditText) findViewById(R.id.recipient);
        phone =(EditText)findViewById(R.id.phone);
        address=(EditText) findViewById(R.id.address);
        qq=(EditText) findViewById(R.id.qq);
        remark=(EditText) findViewById(R.id.remark);
        Bundle bundle = getIntent().getExtras();
        value = bundle.getStringArray("value");
        course_price=(TextView) findViewById(R.id.course_price);
        diamond_amount=(TextView) findViewById(R.id.diamond_amount);//应支付
        discount_price=(TextView) findViewById(R.id.discount_price);//折扣
       // diamond_amount.setText(value[0]);
        course_price.setText(value[0]);
        dd = Double.valueOf(value[0].substring(1,value[0].length()));
        var3 = getApplication().getSharedPreferences("umeng_comm_user_info", 0);
         String str=Double.toString(dd);
          diamond_amount.setText("￥"+str);
        actionbar_left_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
    public void pay(View v) {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }
 		String price = diamond_amount.getText().toString();//价格
        String orderInfo ="";
        if(!price.isEmpty()&& !price.equals("￥")) {
//		String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
          orderInfo = getOrderInfo(value[1], "商品名称是:" + value[1] + "；\n原价是:" + value[0] + "；\n折扣是：" + discount_price.getText().toString() + "；\n最终售价为：" + diamond_amount.getText().toString() + "\n用户真实姓名是：" + recipient.getText().toString() + "" + "\n用户手机号是：" + phone.getText().toString() + "" + "\n用户地址是：" + address.getText().toString() + "" + "\n备注是：" + remark.getText().toString(), price.substring(1, price.length()));
        }
        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(GoodsPayDemoActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     * @param v
     */
    public void h5Pay(View v) {
        Intent intent = new Intent(this, H5PayDemoActivity.class);
        Bundle extras = new Bundle();
        /**
         * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
         * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
         * 商户可以根据自己的需求来实现
         */
        String url = "http://m.taobao.com";
        // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo(String subject, String body, String price) {
        Log.i("price","subject="+subject+"/n price="+price);
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
