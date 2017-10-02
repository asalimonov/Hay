package com.github.asalimonov.hay.core.endpoints.http.routes

import com.github.asalimonov.hay.core.Configuration
import io.netty.handler.codec.http.{HttpMethod, HttpRequest}

class RouteRegistry (val routeMap: Map[(HttpMethod, String), (Configuration) => ContentProvider],
                     val contentProvider404: (Configuration) => ContentProvider,
                     val contentProvider403: (Configuration) => ContentProvider,
                     val contentProvider500: (Configuration) => ContentProvider,
                    ){

  def getContentProvider(request: HttpRequest, configuration: Configuration): ContentProvider = {
    require(request != null, "HttpRequest cannot be null")
    val tmp = routeMap.getOrElse((request.method, request.uri.toLowerCase), contentProvider404)
    tmp.apply(configuration)
  }


}
