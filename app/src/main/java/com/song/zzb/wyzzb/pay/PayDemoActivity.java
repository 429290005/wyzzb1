package com.song.zzb.wyzzb.pay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.bean.Vip;
import com.song.zzb.wyzzb.bean.VipRecord;
import com.song.zzb.wyzzb.util.SharedPrefUtils;
import com.song.zzb.wyzzb.util.ToastUtil;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class PayDemoActivity extends FragmentActivity {
   private TextView ok,oo,ol,oq,or,ot,ov,om;
	private GridView oh,og;
	private RadioButton manButton,womanButton;
	private RadioGroup genderGroup;
	private ImageView actionbar_left_menu;
	private int price;
	private String dsc;//后台描述
	private List<Map<String, Object>> yeardata_list,monthdata_list;
	private SimpleAdapter year_adapter,month_adapter;
	// 图片封装为一个数组
	private int[] iconmonth = { R.drawable.n9, R.drawable.ny,
			R.drawable.ne, R.drawable.nb, R.drawable.nc};
	private int[] iconyear = { R.drawable.na,
			R.drawable.nd, R.drawable.nf, R.drawable.ng,
			 };
	private String[] iconmonthName = { "多家机构课程", "会员课免费", "高清视频", "学习问答", "尊贵身份"};
	private String[] iconyearName = { "无限下载", "视频缓存", "课程需求", "年费图标"};
	// 商户PID
	public static final String PARTNER = "2088502275420434";
	// 商户收款账号
	public static final String SELLER = "429290005@qq.com";
	// 商户私钥，pkcs8格式

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
					p2.setNickname(SharedPrefUtils.getLoginUser(getApplicationContext()).getUsername());
					p2.setPrice(price+"元");
					p2.setSystemflag(SharedPrefUtils.getLoginUser(getApplicationContext()).getUsername());
					p2.setGoodsds(dsc+""+price);
					p2.save(PayDemoActivity.this);
					ToastUtil.showToast(getApplication(),"您已成功开通"+ok.getText().toString());


				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(PayDemoActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

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
		setContentView(R.layout.pay_main);

		actionbar_left_menu=(ImageView)findViewById(R.id.actionbar_left_menu);
		oh=(GridView)findViewById(R.id.oh);//年度
		og=(GridView)findViewById(R.id.og);//月度
		genderGroup = (RadioGroup)findViewById(R.id.oi);
		manButton = (RadioButton)findViewById(R.id.on);//月度
		womanButton = (RadioButton)findViewById(R.id.ou);//年度
		ok =(TextView) findViewById(R.id.ok);//月度会员标题
		oo=(TextView) findViewById(R.id.oo);//少吃两顿的饭
		ol=(TextView) findViewById(R.id.ol);//月度会员价格
		om=(TextView) findViewById(R.id.ov);//月度会员价原价
		oq=(TextView) findViewById(R.id.oq);//年度黄金会员标题
		or=(TextView) findViewById(R.id.or);//会员现价
		ot=(TextView) findViewById(R.id.ot);//会员价格原价
		ov=(TextView) findViewById(R.id.ov);//会员
		yeardata_list = new ArrayList<Map<String, Object>>();
		monthdata_list = new ArrayList<Map<String, Object>>();
		//获取数据
		getyearData();
		getmonthData();
		//新建适配器
		String [] from ={"image","text"};
		int [] to = {R.id.im,R.id.ja};
		year_adapter = new SimpleAdapter(this, yeardata_list, R.layout.gc_item, from, to);
		month_adapter = new SimpleAdapter(this, monthdata_list, R.layout.gc_item, from, to);
		//配置适配器
		oh.setAdapter(month_adapter);
		og.setAdapter(year_adapter);
		queryMonthVip();
		queryYearVip();

		actionbar_left_menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
	//年度图标
	public List<Map<String, Object>> getmonthData(){
		//cion和iconName的长度是相同的，这里任选其一都可以
		for(int i=0;i<iconmonth.length;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", iconmonth[i]);
			map.put("text", iconmonthName[i]);

			monthdata_list.add(map);
		}

		return monthdata_list;
	}
	//月度图标
	public List<Map<String, Object>> getyearData(){
		//cion和iconName的长度是相同的，这里任选其一都可以
		for(int i=0;i<iconyear.length;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", iconyear[i]);
			map.put("text", iconyearName[i]);
			yeardata_list.add(map);
		}
		return yeardata_list;

	}
	/*
   年度会员
      */
	private void queryYearVip() {
		// TODO Auto-generated method stub
		BmobQuery<Vip> newsQuery = new BmobQuery<Vip>();
		//按照时间降序
		newsQuery.addWhereEqualTo("flag", "3");
//		newsQuery.order("orderby");

		newsQuery.findObjects(getApplicationContext(), new FindListener<Vip>() {

			@Override
			public void onSuccess(final List<Vip> data) {
				// TODO Auto-generated method stub
				if (data != null) {


					oq.setText(data.get(0).getNamedsc());//年度黄金会员标题
					or.setText(data.get(0).getMallprice());//会员现价
					ot.setText(data.get(0).getVipprice());//会员价格原价
					ot.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
					ov.setText(data.get(0).getName());//会员价
					genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(RadioGroup group, int checkedId) {
							if (checkedId == womanButton.getId()){
								dsc=oq.getText().toString();

								womanButton.setChecked(true);
								if(!TextUtils.isEmpty(data.get(0).getVipprice())){
									price= Integer.parseInt(data.get(0).getVipprice().substring(0,data.get(0).getVipprice().length()-1));
								}

							}

						}
					});
//                    courseList.addAll(data);
//					tiKuBaseAdapter = new TiKuExciseAdapter(getApplicationContext(), data);
//					tiKuBaseAdapter.notifyDataSetChanged();
//					listView.setAdapter(tiKuBaseAdapter);
//					loadview.setVisibility(View.GONE);

				}else {
					ToastUtil.showToast(getApplicationContext(),"加载失败，请重试");
//					loadview.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onError(int errorCode, String errorString) {
				// TODO Auto-generated method stub
				//showToast(errorString);
			}
		});
	}
	/*
    月度会员
      */
	private void queryMonthVip() {
		// TODO Auto-generated method stub
		BmobQuery<Vip> newsQuery = new BmobQuery<Vip>();
		//按照时间降序
		newsQuery.addWhereEqualTo("flag", "2");
//		newsQuery.order("orderby");

		newsQuery.findObjects(getApplicationContext(), new FindListener<Vip>() {

			@Override
			public void onSuccess(final List<Vip> data) {
				// TODO Auto-generated method stub
				if (data != null) {

					ok.setText(data.get(0).getNamedsc());//月度会员标题
					oo.setText(data.get(0).getName());//少吃两顿的饭
					ol.setText(data.get(0).getMallprice());//月度会员价格
					om.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
					om.setText(data.get(0).getVipprice());

					genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(RadioGroup group, int checkedId) {
							if (checkedId == manButton.getId()){

								dsc=ok.getText().toString();
								manButton.setChecked(true);
								if(!TextUtils.isEmpty(data.get(0).getVipprice())){
									price= Integer.parseInt(data.get(0).getVipprice().substring(0,data.get(0).getVipprice().length()-1));
								}

							}

						}
					});
//                    courseList.addAll(data);
//					tiKuBaseAdapter = new TiKuExciseAdapter(getApplicationContext(), data);
//					tiKuBaseAdapter.notifyDataSetChanged();
//					listView.setAdapter(tiKuBaseAdapter);
//					loadview.setVisibility(View.GONE);

				}else {
					ToastUtil.showToast(getApplicationContext(),"加载失败，请重试");
//					loadview.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onError(int errorCode, String errorString) {
				// TODO Auto-generated method stub
				//showToast(errorString);
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
//		String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
		String orderInfo = getOrderInfo(dsc, dsc, ""+price);
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
				PayTask alipay = new PayTask(PayDemoActivity.this);
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
