
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/niles/IdeaProjects/HeadyTypesafeProject/conf/routes
// @DATE:Tue Oct 13 16:52:14 IST 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseEntityController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def addCategory: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.EntityController.addCategory",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "category"})
        }
      """
    )
  
    // @LINE:7
    def getCategories: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.EntityController.getCategories",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "categories"})
        }
      """
    )
  
    // @LINE:10
    def updateProduct: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.EntityController.updateProduct",
      """
        function() {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "updateProduct"})
        }
      """
    )
  
    // @LINE:9
    def addProduct: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.EntityController.addProduct",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "product"})
        }
      """
    )
  
    // @LINE:8
    def getProducts: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.EntityController.getProducts",
      """
        function(categoryId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getProducts/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("categoryId", categoryId0))})
        }
      """
    )
  
  }


}
