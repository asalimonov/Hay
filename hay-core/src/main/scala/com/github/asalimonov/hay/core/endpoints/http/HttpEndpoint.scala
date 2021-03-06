package com.github.asalimonov.hay.core.endpoints.http

import com.github.asalimonov.hay.core.Configuration
import com.github.asalimonov.hay.core.endpoints.Endpoint
import com.github.asalimonov.hay.core.endpoints.http.routes.RouteRegistry
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.SelfSignedCertificate

class HttpEndpoint(httpConfig: HttpEndpointConfig, configuration: Configuration) extends Endpoint {

  var sslCtx: SslContext = _
  private val logger = configuration.logger

  def start(): Unit = {

    if (httpConfig.ssl) {
      val ssc = new SelfSignedCertificate()
      sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build()
    }

    // Configure the server.
    val bossGroup = new NioEventLoopGroup(1)
    val workerGroup = new NioEventLoopGroup()

    try{

      import java.net.InetSocketAddress
      val inet = new InetSocketAddress(httpConfig.port)
      val routeRegistry = new RouteRegistry(configuration)

      val b = new ServerBootstrap()
      b.group(bossGroup, workerGroup)
        .channel(classOf[NioServerSocketChannel])
        .handler(new LoggingHandler(LogLevel.INFO))
        .childHandler(new HttpServerInitializer(sslCtx, routeRegistry, configuration))

      val ch = b.bind(inet).sync().channel()

      ch.closeFuture().sync()

    }catch  {
      case e: Exception => logger.error(s"Couldn't create HTTP Endpoint: ${e.getMessage}", e)
    }
    finally {
      bossGroup.shutdownGracefully()
      workerGroup.shutdownGracefully()
    }
  }

  def stop(): Unit ={
    throw new NotImplementedError()
  }
}
