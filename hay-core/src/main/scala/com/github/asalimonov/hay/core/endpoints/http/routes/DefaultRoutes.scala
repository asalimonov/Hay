package com.github.asalimonov.hay.core.endpoints.http.routes

import com.github.asalimonov.hay.core.Configuration
import com.github.asalimonov.hay.core.endpoints.http.routes.v1.{About, Health, RootPage}
import io.netty.handler.codec.http.{HttpMessage, HttpMethod}

class DefaultRoutes(val configuration: Configuration) {

  val contentProvider404: (Configuration) => ContentProvider = null

  val routes: Map[(HttpMethod, String), (Configuration) => ContentProvider] = Map(
    ((HttpMethod.GET, "/"), (c: Configuration) => new RootPage(configuration)),
    //v1
    ((HttpMethod.GET, "/v1/about"), (c: Configuration) => new About()),
    ((HttpMethod.GET, "/v1/health"), (c: Configuration) => new Health(configuration))
  )

}
