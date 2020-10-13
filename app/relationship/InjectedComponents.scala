package relationship

import client.MongoClientRepo
import configuration.{MongoProperties, _}
import controllers.EntityController
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.mvc.EssentialFilter
import repo.EntityRepo
import router.Routes


class InjectedComponents(context: Context) extends BuiltInComponentsFromContext(context)
  with controllers.AssetsComponents with AhcWSComponents {

  // configuration
  lazy val properties = new Properties(configuration)
  lazy val mongoProperties = new MongoProperties(properties)

  //DB
  lazy val dbManager = new MongoClientRepo(mongoProperties)
  lazy val entityRepository = new EntityRepo(dbManager)

  //controllers
  lazy val entity: EntityController = new EntityController(controllerComponents, entityRepository)


  override def httpFilters: Seq[EssentialFilter] = Seq()

  override def router = new Routes(httpErrorHandler, entity)
}
