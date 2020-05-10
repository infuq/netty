package com.example.thread.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class Client {

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
//                .option(ChannelOption.SO_TIMEOUT, 2)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        EventLoopGroup businessGroup = new NioEventLoopGroup();

                        ChannelPipeline channelPipeline = ch.pipeline();
                        channelPipeline.addLast(new StringDecoder());
                        channelPipeline.addLast(new StringEncoder());
                        channelPipeline.addLast(businessGroup, new ClientInHandler());//自定义Handler

                    }
                });


        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));


    }

}
