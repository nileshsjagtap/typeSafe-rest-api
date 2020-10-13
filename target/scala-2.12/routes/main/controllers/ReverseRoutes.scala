
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/niles/IdeaProjects/HeadyTypesafeProject/conf/routes
// @DATE:Tue Oct 13 16:52:14 IST 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseEntityController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def addCategory(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "category")
    }
  
    // @LINE:7
    def getCategories(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "categories")
    }
  
    // @LINE:10
    def updateProduct(): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "updateProduct")
    }
  
    // @LINE:9
    def addProduct(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "product")
    }
  
    // @LINE:8
    def getProducts(categoryId:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "getProducts/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("categoryId", categoryId)))
    }
  
  }


}
