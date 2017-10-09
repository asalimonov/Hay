package com.github.asalimonov.hay.core.endpoints.http.routes.v1

import java.nio.ByteBuffer

import com.github.asalimonov.hay.core.Configuration
import com.github.asalimonov.hay.core.endpoints.http.routes.ContentProvider
import io.netty.buffer.{Unpooled}
import io.netty.handler.codec.http._

class Health(val configuration: Configuration) extends ContentProvider {
  override def getContent(request: HttpRequest, message: HttpMessage): FullHttpResponse = {
    val response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
      Unpooled.wrappedBuffer(getSnapshot))
    response.headers.set(HttpHeaderNames.CONTENT_TYPE, "application/json")
    response.headers.set(HttpHeaderNames.CONTENT_LENGTH, response.content.readableBytes)
    response
  }

  def getSnapshot():  ByteBuffer = {
    ByteBuffer.wrap("{\"msg\":\"Not implemented\"}".getBytes())
  }
}
