package com.github.asalimonov.hay.core.endpoints.http.routes.v1

import java.io.InputStream

import com.github.asalimonov.hay.core.endpoints.http.routes.ContentProvider
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http._

class Page404 extends ContentProvider {
  override def getContent(request: HttpRequest, message: HttpMessage): FullHttpResponse = {

    val response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND,
      Unpooled.wrappedBuffer(slurp("/pages/v1/404.html")))
    response.headers.set(HttpHeaderNames.CONTENT_TYPE, "text/html")
    response.headers.set(HttpHeaderNames.CONTENT_LENGTH, response.content.readableBytes)
    response
  }

  def slurp(resource: String) = {
    val stream : InputStream = getClass.getResourceAsStream(resource)
    scala.io.Source.fromInputStream( stream ).toArray.map(_.toByte)
  }
}
