package controllers

import core.{Category, UNABLE_TO_PARSE_JSON, `Product`}
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import repo.EntityRepo
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class EntityController @Inject()(cc: ControllerComponents, entityRepo: EntityRepo) extends AbstractController(cc) {

  def addCategory: Action[AnyContent] = Action.async {
    request => {
      request.body.asJson.map { categoryJson =>
        val categoryId = categoryJson.\("categoryId").as[Int]
        val parentId = categoryJson.\("parentId").asOpt[Int]
        val productIds = categoryJson.\("productIds").as[List[Int]]
        val category = Category(categoryId, parentId, productIds)
        entityRepo.addCategory(category).map {
          case Right(categoryId) => Ok(Json.toJson(categoryId))
          case Left(exception) => Ok(exception.toString)
        }
      }.getOrElse(Future.successful(BadRequest(UNABLE_TO_PARSE_JSON.toString)))
    }
  }

  def getCategories: Action[AnyContent] = Action.async {
    _ => {
      entityRepo.getCategories.map {
        case Right(categories) => Ok(Json.toJson(categories))
        case Left(exception) => NotFound(exception.toString)
      }
    }
  }

  def addProduct: Action[AnyContent] = Action.async {
    request => {
      request.body.asJson.map { entityJson =>
        val categoryId = entityJson.\("categoryId").as[Int]
        val productId = entityJson.\("productId").as[Int]
        val productName = entityJson.\("productName").as[String]
        val price = entityJson.\("price").as[Int]
        val product = `Product`(productId, productName, price)
        entityRepo.addProduct(categoryId, product).map {
          case Right(productId) => Ok(Json.toJson(productId))
          case Left(exception) => Ok(exception.toString)
        }
      }.getOrElse(Future.successful(BadRequest(UNABLE_TO_PARSE_JSON.toString)))
    }
  }

  def getProducts(categoryId: Int): Action[AnyContent] = Action.async {
    _ => {
      entityRepo.getProducts(categoryId).map {
        case Right(products) => Ok(Json.toJson(products))
        case Left(exception) => NotFound(exception.toString)
      }
    }
  }

  def updateProduct: Action[AnyContent] = Action.async {
    request => {
      request.body.asJson.map { productJson =>
        val productId = productJson.\("productId").as[Int]
        val productName = productJson.\("productName").as[String]
        val price = productJson.\("price").as[Int]
        val product = `Product`(productId, productName, price)
        entityRepo.updateProduct(productId, product).map {
          case Right(productId) => Ok(Json.toJson(productId))
          case Left(exception) => Ok(exception.toString)
        }
      }.getOrElse(Future.successful(BadRequest(UNABLE_TO_PARSE_JSON.toString)))
    }
  }
}
