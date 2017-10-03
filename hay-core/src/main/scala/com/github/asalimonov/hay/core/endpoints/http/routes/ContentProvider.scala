package com.github.asalimonov.hay.core.endpoints.http.routes

import io.netty.handler.codec.http.{FullHttpResponse, HttpMessage, HttpRequest}

trait ContentProvider {
  def getContent(request: HttpRequest, message: HttpMessage): FullHttpResponse
}
