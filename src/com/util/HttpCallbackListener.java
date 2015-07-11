package com.util;
/*
 * HttpUtil 类中使用到了HttpCallbackListener 接口来回调服务返回的结果，因此我们还需
 *要在util 包下添加这个接口.
 * */
public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
