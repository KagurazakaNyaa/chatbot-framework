package com.kagurazakanyaa.nyaabot.driver.mirai.model;

import com.kagurazakanyaa.nyaabot.api.model.Channel;
import com.kagurazakanyaa.nyaabot.api.model.Credential;
import com.kagurazakanyaa.nyaabot.api.model.Message;
import com.kagurazakanyaa.nyaabot.driver.mirai.MiraiDriver;

/**
 * QQ消息频道
 * 
 * @author KagurazakaNyaa <i@kagurazakanyaa.com>
 *
 */
public class MiraiChannel extends Channel {

	/**
	 * 构造函数
	 * 
	 * @param channelName   频道名，此处应为QQ号或QQ群号
	 * @param channelDriver 驱动实例
	 */
	public MiraiChannel(String channelName, MiraiDriver channelDriver) {
		super(channelName, channelDriver);
	}

	/**
	 * 构造函数
	 * 
	 * @param channelName       频道名，此处应为QQ号或QQ群号
	 * @param channelDriver     驱动实例
	 * @param channelCredential 登录凭据，此处应为QQ号和密码
	 */
	public MiraiChannel(String channelName, MiraiDriver channelDriver, Credential channelCredential) {
		super(channelName, channelDriver, channelCredential);
	}

	/**
	 * 发送消息到此频道
	 */
	@Override
	public Boolean sendMessage(Message<?> message) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 登录
	 */
	@Override
	public Boolean login(Credential channelCredential) {
		// TODO Auto-generated method stub
		return null;
	}

}
