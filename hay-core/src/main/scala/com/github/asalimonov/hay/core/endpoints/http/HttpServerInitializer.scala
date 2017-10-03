package com.github.asalimonov.hay.core.endpoints.http

import com.github.asalimonov.hay.core.Configuration
import com.github.asalimonov.hay.core.endpoints.http.routes.RouteRegistry
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.ssl.SslContext
import io.netty.handler.codec.http.HttpRequestDecoder
import io.netty.handler.codec.http.HttpResponseEncoder

class HttpServerInitializer(val sslCtx: SslContext, val routeRegistry: RouteRegistry, val configuration: Configuration)  extends ChannelInitializer[SocketChannel] {

  def initChannel(ch: SocketChannel): Unit = {
    val p = ch.pipeline
    if(sslCtx != null){
      p.addLast(sslCtx.newHandler(ch.alloc()))
    }

    p.addLast(new HttpRequestDecoder)
    p.addLast(new HttpResponseEncoder)
    p.addLast(new HttpServerHandler(routeRegistry, configuration))
  }
}
