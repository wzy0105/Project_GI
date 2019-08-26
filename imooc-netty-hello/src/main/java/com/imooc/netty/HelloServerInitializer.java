package com.imooc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description: ��ʼ������channelע��󣬻�ִ���������Ӧ�ĳ�ʼ������
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// ͨ��SocketChannelȥ��ö�Ӧ�Ĺܵ�
		ChannelPipeline pipeline = channel.pipeline();
		
		// ͨ���ܵ������handler
		// HttpServerCodec����netty�Լ��ṩ�������࣬�������Ϊ������
		// �����󵽷���ˣ�������Ҫ�����룬��Ӧ���ͻ���������
		pipeline.addLast("HttpServerCodec", new HttpServerCodec());
		
		// ����Զ���������࣬���� "hello netty~"
		pipeline.addLast("customHandler", new CustomHandler());
	}

}
