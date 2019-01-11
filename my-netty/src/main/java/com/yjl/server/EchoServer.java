package com.yjl.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	
	private final int port;
	
	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws InterruptedException {
		int port = 9898;
		new EchoServer(port).start();

	}
	public void start() throws InterruptedException {
		final EchoServerHandler echoServerHandler = new EchoServerHandler();
		//����EventLoopGroup
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//����ServerBootstrap
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(group)
			         //ָ��NioServerSocketChannel����Channel
			         .channel(NioServerSocketChannel.class)
			         //ʹ��ָ���Ķ˿������׽��ֵ�ַ
			         .localAddress(new InetSocketAddress(port))
			         //���һ��echoServerHandler��Channelpipeline
			         .childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel sc) throws Exception {
							sc.pipeline().addLast(echoServerHandler);
						}
					});
			//�첽�󶨷�����������sync()�����ȴ���ֱ�������
			ChannelFuture cf = bootstrap.bind().sync();
			//��ȡChannel��closeFuture����������ǰ�߳�ֱ�������
			cf.channel().closeFuture().sync();
		} finally {
			//�ر�EventLoopGroup���ͷ�������Դ
			group.shutdownGracefully().sync();
		}
		
	}
	
	

}
