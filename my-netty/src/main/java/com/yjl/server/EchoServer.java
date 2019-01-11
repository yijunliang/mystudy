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
		//创建EventLoopGroup
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//创建ServerBootstrap
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(group)
			         //指定NioServerSocketChannel传输Channel
			         .channel(NioServerSocketChannel.class)
			         //使用指定的端口设置套接字地址
			         .localAddress(new InetSocketAddress(port))
			         //添加一个echoServerHandler到Channelpipeline
			         .childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel sc) throws Exception {
							sc.pipeline().addLast(echoServerHandler);
						}
					});
			//异步绑定服务器，调用sync()阻塞等待，直到绑定完成
			ChannelFuture cf = bootstrap.bind().sync();
			//获取Channel的closeFuture并且阻塞当前线程直到它完成
			cf.channel().closeFuture().sync();
		} finally {
			//关闭EventLoopGroup，释放所有资源
			group.shutdownGracefully().sync();
		}
		
	}
	
	

}
