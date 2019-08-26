package com.imooc.netty.websocket;

import java.time.LocalDateTime;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 
 * @Description: ������Ϣ��handler
 * TextWebSocketFrame�� ��netty�У�������Ϊwebsocketר�Ŵ����ı��Ķ���frame����Ϣ������
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	// ���ڼ�¼�͹������пͻ��˵�channle
	private static ChannelGroup clients = 
			new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) 
			throws Exception {
		// ��ȡ�ͻ��˴����������Ϣ
		String content = msg.text();
		System.out.println("���ܵ������ݣ�" + content);
		
//		for (Channel channel: clients) {
//			channel.writeAndFlush(
//				new TextWebSocketFrame(
//						"[��������]" + LocalDateTime.now() 
//						+ "���ܵ���Ϣ, ��ϢΪ��" + content));
//		}
		// ��������������������forѭ����һ��
		clients.writeAndFlush(
				new TextWebSocketFrame(
						"[��������]" + LocalDateTime.now() 
						+ "���ܵ���Ϣ, ��ϢΪ��" + content));
		
	}

	/**
	 * ���ͻ������ӷ����֮�󣨴����ӣ�
	 * ��ȡ�ͻ��˵�channle�����ҷŵ�ChannelGroup��ȥ���й���
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		clients.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// ������handlerRemoved��ChannelGroup���Զ��Ƴ���Ӧ�ͻ��˵�channel
//		clients.remove(ctx.channel());
		System.out.println("�ͻ��˶Ͽ���channle��Ӧ�ĳ�idΪ��" 
							+ ctx.channel().id().asLongText());
		System.out.println("�ͻ��˶Ͽ���channle��Ӧ�Ķ�idΪ��" 
							+ ctx.channel().id().asShortText());
	}

	
	
}
