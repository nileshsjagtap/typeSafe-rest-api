
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/niles/IdeaProjects/HeadyTypesafeProject/conf/routes
// @DATE:Tue Oct 13 16:52:14 IST 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  EntityController_0: controllers.EntityController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    EntityController_0: controllers.EntityController
  ) = this(errorHandler, EntityController_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, EntityController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """category""", """controllers.EntityController.addCategory"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """categories""", """controllers.EntityController.getCategories"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getProducts/""" + "$" + """categoryId<[^/]+>""", """controllers.EntityController.getProducts(categoryId:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """product""", """controllers.EntityController.addProduct"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateProduct""", """controllers.EntityController.updateProduct"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_EntityController_addCategory0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("category")))
  )
  private[this] lazy val controllers_EntityController_addCategory0_invoker = createInvoker(
    EntityController_0.addCategory,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.EntityController",
      "addCategory",
      Nil,
      "POST",
      this.prefix + """category""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_EntityController_getCategories1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("categories")))
  )
  private[this] lazy val controllers_EntityController_getCategories1_invoker = createInvoker(
    EntityController_0.getCategories,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.EntityController",
      "getCategories",
      Nil,
      "GET",
      this.prefix + """categories""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_EntityController_getProducts2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getProducts/"), DynamicPart("categoryId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_EntityController_getProducts2_invoker = createInvoker(
    EntityController_0.getProducts(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.EntityController",
      "getProducts",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """getProducts/""" + "$" + """categoryId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_EntityController_addProduct3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("product")))
  )
  private[this] lazy val controllers_EntityController_addProduct3_invoker = createInvoker(
    EntityController_0.addProduct,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.EntityController",
      "addProduct",
      Nil,
      "POST",
      this.prefix + """product""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_EntityController_updateProduct4_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateProduct")))
  )
  private[this] lazy val controllers_EntityController_updateProduct4_invoker = createInvoker(
    EntityController_0.updateProduct,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.EntityController",
      "updateProduct",
      Nil,
      "PUT",
      this.prefix + """updateProduct""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_EntityController_addCategory0_route(params@_) =>
      call { 
        controllers_EntityController_addCategory0_invoker.call(EntityController_0.addCategory)
      }
  
    // @LINE:7
    case controllers_EntityController_getCategories1_route(params@_) =>
      call { 
        controllers_EntityController_getCategories1_invoker.call(EntityController_0.getCategories)
      }
  
    // @LINE:8
    case controllers_EntityController_getProducts2_route(params@_) =>
      call(params.fromPath[Int]("categoryId", None)) { (categoryId) =>
        controllers_EntityController_getProducts2_invoker.call(EntityController_0.getProducts(categoryId))
      }
  
    // @LINE:9
    case controllers_EntityController_addProduct3_route(params@_) =>
      call { 
        controllers_EntityController_addProduct3_invoker.call(EntityController_0.addProduct)
      }
  
    // @LINE:10
    case controllers_EntityController_updateProduct4_route(params@_) =>
      call { 
        controllers_EntityController_updateProduct4_invoker.call(EntityController_0.updateProduct)
      }
  }
}
