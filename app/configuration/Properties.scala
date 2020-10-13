package configuration

import javax.inject.Inject
import play.api.Configuration

class Properties @Inject()(config: Configuration) {

  lazy val url: String = requireProperty("db.properties.url")
  lazy val mongoDBHostNames: String = requireProperty("db.properties.mongoDBHostNames")
  lazy val mongoDBName: String = requireProperty("db.properties.mongoDBName")
  lazy val user: String = requireProperty("db.properties.user")
  lazy val password: String = requireProperty("db.properties.password")
  private def requireProperty(propName: String): String = {
    config.get[String](propName)
  }

}

class MongoProperties @Inject()(properties: Properties) {
  lazy val url: String = properties.url
  lazy val mongoDBHostNames: String = properties.mongoDBHostNames
  lazy val mongoDBName: String = properties.mongoDBName
  lazy val user: String = properties.user
  lazy val password: String = properties.password
}

