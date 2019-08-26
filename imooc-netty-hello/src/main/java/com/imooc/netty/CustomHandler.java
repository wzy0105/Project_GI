package com.imooc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * @Description: �����Զ���������
 */
// SimpleChannelInboundHandler: ����������������ʵ�൱��[��վ���뾳]
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) 
			throws Exception {
		// ��ȡchannel
		Channel channel = ctx.channel();
		
		if (msg instanceof HttpRequest) {
			// ��ʾ�ͻ��˵�Զ�̵�ַ
			System.out.println(channel.remoteAddress());
			
			// ���巢�͵�������Ϣ
			ByteBuf content = Unpooled.copiedBuffer("Hello netty~", CharsetUtil.UTF_8);
			
			// ����һ��http response
			FullHttpResponse response = 
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, 
							HttpResponseStatus.OK, 
							content);
			// Ϊ��Ӧ�����������ͺͳ���
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
			
			// ����Ӧˢ���ͻ���
			ctx.writeAndFlush(response);
		}
		
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel������ע��");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel�������Ƴ�");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel��������Ծ");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel����������Ծ");
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channeld��ȡ��ϡ�����");
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("�û��¼�����������");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel��д����");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("�������쳣");
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("���������");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("�������Ƴ�");
		super.handlerRemoved(ctx);
	}

}
