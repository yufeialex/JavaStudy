package com.yufei.languagebasic.io.SocketIO_02.netty.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
        //ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();

        //发送消息
        Thread.sleep(1000);
        // 连发3次，可能变成7 777777 77，出现拆包粘包问题
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));

        // write是把内容写到buffer，flush是把buffer清空发送出去
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
        //cf2.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));

        Thread.sleep(2000);
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
        //cf2.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));

        cf1.channel().closeFuture().sync(); // 这个是在等待异步执行的channel结束
        //cf2.channel().closeFuture().sync();
        group.shutdownGracefully();


    }
}
