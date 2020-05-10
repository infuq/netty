package com.example.mem;

import io.netty.buffer.PooledByteBufAllocator;

public class Example {

    public static void main(String[] args) {


        PooledByteBufAllocator.DEFAULT.directBuffer(256);



    }

}
