package com.yjl.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;

/**
 * ������ҵ���߼�,������ղ���Ӧ�¼�֪ͨ
 * @author Administrator
 *
 */
@Sharable//���Ա����channel��ȫ�ع���
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
    /**
     * ����Ϣ����������˶�����ô˷���
     */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		//����������˽��յ�����Ϣ
		System.out.println("���������յ�����Ϣ:" + in.toString(CharsetUtil.UTF_8));
		//�����յ�����Ϣд����Ϣ������
		ctx.write(in);
	}
    /**
     * ֪ͨChannelInboundHandler���һ�ζ�channelRead�ĵ����ǵ�ǰ������ȡ�е����һ����Ϣ
     */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//���ݴ���ChannelOutboundBuffer�е���Ϣ������һ�ε���flush()��writeAndFlushʱ����д�����׽�����
		//���رո�Channel
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
		   .addListener(ChannelFutureListener.CLOSE);
	}
    /**
     * ��ȡ�������䣬���쳣��Ϣ�׳�ʱ�����
     */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		//�ر�Channel
		ctx.close();
	}
	
	
}
