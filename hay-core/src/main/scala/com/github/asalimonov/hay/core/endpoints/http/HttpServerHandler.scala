package com.github.asalimonov.hay.core.endpoints.http

import com.github.asalimonov.hay.core.{Configuration, Hay}
import com.github.asalimonov.hay.core.endpoints.http.routes.RouteRegistry
import io.netty.channel.{ChannelHandlerContext, SimpleChannelInboundHandler}
import io.netty.channel.ChannelFutureListener
import io.netty.handler.codec.http._

class HttpServerHandler (val routeRegistry: RouteRegistry, val configuration: Configuration) extends SimpleChannelInboundHandler[Object]{

  private val logger = configuration.logger
  private var request: HttpRequest = _
  private val buf = new StringBuilder

  override def channelReadComplete(ctx: ChannelHandlerContext): Unit = {
    ctx.flush()
  }

  override def channelRead0(ctx: ChannelHandlerContext, msg: Object): Unit = {
    if(msg.isInstanceOf[HttpRequest]){
      request = msg.asInstanceOf[HttpRequest]
      val httpMsg = request.asInstanceOf[HttpMessage]
      if(HttpUtil.is100ContinueExpected(httpMsg)){
        ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE))
      }
      val keepAlive = HttpUtil.isKeepAlive(httpMsg)
      val response: FullHttpResponse =
      try {
        val contentProvider = routeRegistry.getContentProvider(request, configuration)
        contentProvider.getContent(request, httpMsg)
      } catch{
        case e: Exception => {
          logger.error("Could handle request", e)
          routeRegistry.contentProvider500.apply(configuration).getContent(request, httpMsg)
        }
      }

      if (!keepAlive) ctx.write(response).addListener(ChannelFutureListener.CLOSE)
      else {
        response.headers.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
        ctx.write(response)
      }
    }
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
    ctx.close()
    logger.error("Exception caught", cause)
  }
}
