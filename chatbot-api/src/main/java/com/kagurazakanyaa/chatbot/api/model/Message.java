package com.kagurazakanyaa.chatbot.api.model;

import lombok.Data;

/**
 * 消息类
 * 
 * @author KagurazakaNyaa <i@kagurazakanyaa.com>
 *
 */
@Data
public abstract class Message {

	/**
	 * 消息的来源频道
	 */
	private final Channel source;

	/**
	 * 消息内容
	 */
	private final String data;
}