package repo

import configuration.MongoProperties
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification


class IntegrationSpecification(implicit ee: ExecutionEnv) extends Specification with Mockito {

  def getMockProperties = {
    val properties = mock[MongoProperties]
    println("Running test against LOCAL")
    properties.mongoDBHostNames returns ""
    properties.user returns ""
    properties.password returns ""
    properties.url returns ""
    properties.mongoDBName returns "entityTest"
    properties
  }

}
