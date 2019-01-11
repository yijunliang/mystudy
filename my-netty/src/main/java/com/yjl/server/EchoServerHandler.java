package com.yjl.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;

/**
 * 服务器业务逻辑,负责接收并响应事件通知
 * @author Administrator
 *
 */
@Sharable//可以被多个channel安全地共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
    /**
     * 有消息到达服务器端都会调用此方法
     */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		//输出服务器端接收到的消息
		System.out.println("服务器接收到的信息:" + in.toString(CharsetUtil.UTF_8));
		//将接收到的消息写给消息发送者
		ctx.write(in);
	}
    /**
     * 通知ChannelInboundHandler最后一次对channelRead的调用是当前批量读取中的最后一条消息
     */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//将暂存于ChannelOutboundBuffer中的消息，在下一次调用flush()或writeAndFlush时尝试写出到套接字中
		//并关闭该Channel
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
		   .addListener(ChannelFutureListener.CLOSE);
	}
    /**
     * 读取操作区间，有异常信息抛出时会调用
     */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		//关闭Channel
		ctx.close();
	}
	
	
}
