package controllers

import core._
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeEach
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._
import repo.EntityRepo

import scala.concurrent.Future

class EntityControllerTest extends Specification with Mockito with BeforeEach {
  sequential

  var entityRepo: EntityRepo = null
  var entityController: EntityController = null

  override def before = {
    entityRepo = mock[EntityRepo]
    entityController = new EntityController(stubControllerComponents(), entityRepo)
  }

  "EntityController" should {
    "route to addCategory method" in {
      routes.EntityController.addCategory().toString() must equalTo(s"/category")
    }
    "route to getCategories method" in {
      routes.EntityController.getCategories().toString() must equalTo(s"/categories")
    }
    "route to addProduct method" in {
      routes.EntityController.addProduct().toString() must equalTo(s"/product")
    }
    "route to getProducts method" in {
      routes.EntityController.getProducts(1).toString() must equalTo(s"/getProducts/1")
    }
    "route to updateProduct method" in {
      routes.EntityController.updateProduct().toString() must equalTo(s"/updateProduct")
    }

    "return categoryId after successfully adding category in DB" in {
      val category = Category(1, Some(3), List(4, 6))
      entityRepo.addCategory(category) returns Future.successful(Right(1))
      val result = entityController.addCategory()(FakeRequest().withJsonBody(Json.parse("""{"categoryId":1, "parentId":3, "productIds": [4,6]}""")))
      status(result) must equalTo(OK)
      contentAsString(result) must equalTo("1")
      there was one(entityRepo).addCategory(category)
    }

    "return Error if add operation fails" in {
      val category = Category(1, Some(3), List(4, 6))
      entityRepo.addCategory(category) returns Future.successful(Left(ErrorWhileAddingCategory))
      val result = entityController.addCategory()(FakeRequest().withJsonBody(Json.parse("""{"categoryId":1, "parentId":3, "productIds": [4,6]}""")))
      status(result) must equalTo(OK)
      contentAsString(result) must equalTo(ErrorWhileAddingCategory.toString)
      there was one(entityRepo).addCategory(category)
    }

    "return all categories with child categories as well" in {
      val categories = List(Category(1, Some(3), List(4, 6)), Category(2, Some(4), List(7, 8)))
      entityRepo.getCategories returns Future.successful(Right(categories))
      val result = entityController.getCategories()(FakeRequest())
      status(result) must equalTo(OK)
      contentAsString(result) must equalTo(Json.toJson(categories).toString())
      there was one(entityRepo).getCategories
    }

    "return error if getCategories operation fails" in {
      val categories = List(Category(1, Some(3), List(4, 6)), Category(2, Some(4), List(7, 8)))
      entityRepo.getCategories returns Future.successful(Left(NoCategoryFoundError("not found")))
      val result = entityController.getCategories()(FakeRequest())
      status(result) must equalTo(NOT_FOUND)
      contentAsString(result) must equalTo(NoCategoryFoundError("not found").toString)
      there was one(entityRepo).getCategories
    }

    "return productId after successfully adding product in DB" in {
      val product = `Product`(1, "watch", 90)
      entityRepo.addProduct(1, product) returns Future.successful(Right(1))
      val result = entityController.addProduct()(FakeRequest().withJsonBody(Json.parse("""{"categoryId":1, "productId":1, "productName":"watch", "price": 90}""")))
      status(result) must equalTo(OK)
      contentAsString(result) must equalTo("1")
      there was one(entityRepo).addProduct(1, product)
    }

    "return Error if add operation fails for product" in {
      val product = `Product`(1, "watch", 90)
      entityRepo.addProduct(1, product) returns Future.successful(Left(ErrorWhileAddingProduct))
      val result = entityController.addProduct()(FakeRequest().withJsonBody(Json.parse("""{"categoryId":1, "productId":1, "productName":"watch", "price": 90}""")))
      status(result) must equalTo(OK)
      contentAsString(result) must equalTo(ErrorWhileAddingProduct.toString)
      there was one(entityRepo).addProduct(1, product)
    }

    "return ProductId if update operation is successful" in {
      val product = `Product`(1, "watch", 90)
      entityRepo.updateProduct(1, product) returns Future.successful(Right(1))
      val result = entityController.updateProduct()(FakeRequest().withJsonBody(Json.parse("""{"categoryId":1, "productId":1, "productName":"watch", "price": 90}""")))
      status(result) must equalTo(OK)
      contentAsString(result) must equalTo("1")
      there was one(entityRepo).updateProduct(1, product)
    }

    "return Error if update operation fails for product" in {
      val product = `Product`(1, "watch", 90)
      entityRepo.updateProduct(1, product) returns Future.successful(Left(ErrorWhileUpdatingProductDetails))
      val result = entityController.updateProduct()(FakeRequest().withJsonBody(Json.parse("""{"categoryId":1, "productId":1, "productName":"watch", "price": 90}""")))
      status(result) must equalTo(OK)
      contentAsString(result) must equalTo(ErrorWhileUpdatingProductDetails.toString)
      there was one(entityRepo).updateProduct(1, product)
    }
  }
}
