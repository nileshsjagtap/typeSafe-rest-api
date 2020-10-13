package repo

import client.MongoClientRepo
import core.{Category, `Product`}
import org.specs2.concurrent.ExecutionEnv
import org.specs2.specification.BeforeEach

import scala.concurrent.Await
import scala.concurrent.duration._

class EntityRepoTest(implicit ee: ExecutionEnv) extends IntegrationSpecification with BeforeEach {
  sequential

  private lazy val mongoRepo = new MongoClientRepo(getMockProperties)
  private lazy val entityRepo = new EntityRepo(mongoRepo)

  def before = {
    entityRepo.dropCollection()
  }

  "Entity Repo " should {
    "insert a category if category is not present in DB" in {
      val category: Category = Category(1, Some(5), List(2, 3))
      val result = Await.result(entityRepo.addCategory(category), 5 seconds)
      result must beRight(1)
    }
    "update a category if category is present in DB" in {
      val expected = List(Category(1, Some(8), List(8, 9)))
      val category1: Category = Category(1, Some(5), List(2, 3))
      val category2: Category = Category(1, Some(8), List(8, 9))
      Await.result(entityRepo.addCategory(category1), 5 seconds)
      Await.result(entityRepo.addCategory(category2), 5 seconds)
      val result = Await.result(entityRepo.getCategories, 5 seconds)
      result must beRight(expected)
    }
    "get all existing categories" in {
      val expected = List(Category(1, Some(5), List(2, 3)), Category(2, Some(8), List(8, 9)))
      val category1: Category = Category(1, Some(5), List(2, 3))
      val category2: Category = Category(2, Some(8), List(8, 9))
      Await.result(entityRepo.addCategory(category1), 5 seconds)
      Await.result(entityRepo.addCategory(category2), 5 seconds)
      val result = Await.result(entityRepo.getCategories, 5 seconds)
      result must beRight(expected)
    }
    "add product under any category or catogories" in {
      val expected = List(Category(1, Some(5), List(3, 2)))
      val category: Category = Category(1, Some(5), List(3))
      val product: Product = `Product`(2, "watch", 50)
      Await.result(entityRepo.addCategory(category), 5 seconds)
      val productRes = Await.result(entityRepo.addProduct(1, product), 5 seconds)
      val result = Await.result(entityRepo.getCategories, 5 seconds)
      productRes must beRight(2)
      result must beRight(expected)
    }
    "update product based on product Id if Product is already present in DB" in {
      val expected = List(Product(2, "Spects", 90))
      val product1: Product = `Product`(2, "watch", 50)
      val product2: Product = `Product`(2, "Spects", 90)
      Await.result(entityRepo.updateProduct(2, product1), 5 seconds)
      Await.result(entityRepo.updateProduct(2, product2), 5 seconds)
      val result = Await.result(entityRepo.findAllProduct, 5 seconds)
      result must beRight(expected)
    }
    "add product based on product Id if Product is not present in DB" in {
      val expected = List(Product(2, "watch", 50), Product(3, "Spects", 90))
      val product1: Product = `Product`(2, "watch", 50)
      val product2: Product = `Product`(3, "Spects", 90)
      Await.result(entityRepo.updateProduct(2, product1), 5 seconds)
      Await.result(entityRepo.updateProduct(3, product2), 5 seconds)
      val result = Await.result(entityRepo.findAllProduct, 5 seconds)
      result must beRight(expected)
    }
  }
}
