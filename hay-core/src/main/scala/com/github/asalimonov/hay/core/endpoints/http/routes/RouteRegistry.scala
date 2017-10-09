package com.github.asalimonov.hay.core.endpoints.http.routes

import com.github.asalimonov.hay.core.Configuration
import com.github.asalimonov.hay.core.endpoints.http.routes.v1._
import io.netty.handler.codec.http.{HttpMethod, HttpRequest}

class RouteRegistry (configuration: Configuration){

  val contentProvider404: (Configuration) => ContentProvider = (configuration: Configuration) => new Page404()
  val contentProvider500: (Configuration) => ContentProvider = (configuration: Configuration) => new Page500()

  private val routes: Map[(HttpMethod, String), (Configuration) => ContentProvider] = Map(
    ((HttpMethod.GET, "/"), (c: Configuration) => new RootPage(configuration)),
    //v1
    ((HttpMethod.GET, "/v1/about"), (c: Configuration) => new About()),
    ((HttpMethod.GET, "/v1/health"), (c: Configuration) => new Health(configuration))
  )

  def getContentProvider(request: HttpRequest, configuration: Configuration): ContentProvider = {
    require(request != null, "HttpRequest cannot be null")
    val contentProvider = routes.getOrElse((request.method, request.uri.toLowerCase), contentProvider404)
    contentProvider.apply(configuration)
  }

}
