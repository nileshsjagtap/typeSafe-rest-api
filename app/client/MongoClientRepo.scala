package client

import configuration.MongoProperties
import javax.inject.Inject
import org.mongodb.scala.MongoClient

class MongoClientRepo @Inject()(properties: MongoProperties) {

  def getDbClient = if (properties.mongoDBHostNames.isEmpty)
    MongoClient("mongodb://localhost")
  else
    MongoClient(
      s"""mongodb://${properties.user}:${properties.password}
      @${properties.mongoDBHostNames.mkString(",")}/${properties.mongoDBName}?authMechanism=SCRAM-SHA-1""")

  private lazy val mongoClient = getDbClient

  def getDatabase = mongoClient.getDatabase(properties.mongoDBName)

}
