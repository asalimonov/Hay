package com.github.asalimonov.hay.core.endpoints.http.routes.v1

import com.github.asalimonov.hay.core.endpoints.http.routes.ContentProvider
import io.netty.handler.codec.http.{FullHttpResponse, HttpMessage, HttpRequest}

class About extends ContentProvider {
  override def getContent(request: HttpRequest, message: HttpMessage): FullHttpResponse = {
    null
  }
}
