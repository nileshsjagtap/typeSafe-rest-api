// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/niles/IdeaProjects/HeadyTypesafeProject/conf/routes
// @DATE:Tue Oct 13 16:32:58 IST 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
