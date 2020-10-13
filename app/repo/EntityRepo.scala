package repo

import client.MongoClientRepo
import core.{ErrorWhileAddingProduct, _}
import javax.inject.Inject
import org.bson.Document
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.UpdateOptions

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

class EntityRepo @Inject()(mongoRepo: MongoClientRepo) {

  private val categoryCodecRegistry = fromRegistries(fromProviders(classOf[Category]), DEFAULT_CODEC_REGISTRY)
  private val productCodecRegistry = fromRegistries(fromProviders(classOf[`Product`]), DEFAULT_CODEC_REGISTRY)
  private lazy val db = mongoRepo.getDatabase
  private lazy val categoryCollection: MongoCollection[Category] = db.withCodecRegistry(categoryCodecRegistry).getCollection("categories")
  private lazy val productCollection: MongoCollection[`Product`] = db.withCodecRegistry(productCodecRegistry).getCollection("products")

  def addCategory(category: Category): Future[Either[ErrorWhileAddingCategory.type, Int]] = {
    categoryCollection.updateOne(new Document("categoryId", category.categoryId),
      new Document("$set", category),
      UpdateOptions().upsert(true)
    ).toFuture().map(_ => Right(category.categoryId)).recover {
      case _: Throwable => Left(ErrorWhileAddingCategory)
    }
  }

  def getCategories: Future[Either[NoCategoryFoundError, Seq[Category]]] = {
    categoryCollection.find().toFuture().map(a => Right(a)).recover {
      case e: Throwable => Left(NoCategoryFoundError(e.toString))
    }
  }

  def addProduct(categoryId: Int, product: `Product`): Future[Either[ErrorWhileAddingProduct.type, Int]] = {
    for {
      _ <- productCollection.updateOne(new Document("productId", product.productId), new Document("$set", product), UpdateOptions().upsert(true)).toFuture()
      either <- categoryCollection.updateMany(equal("categoryId", categoryId), com.mongodb.client.model.Updates.addToSet("productIds", product.productId)).toFuture()
        .map(_ => Right(product.productId)).recover {
        case e: Throwable => Left(ErrorWhileAddingProduct)
      }
    } yield either
  }

  def getProducts(categoryId: Int): Future[Either[NoProductFoundError, Seq[`Product`]]] = {
    for {
      categories <- categoryCollection.find(equal("categoryId", categoryId)).toFuture().map(_.map(_.productIds))
      either <- productCollection.find(in("productId", categories.flatten.distinct)).toFuture()
        .map(products => Right(products)).recover {
        case e: Throwable => Left(NoProductFoundError(e.toString))
      }
    } yield either
  }

  def updateProduct(productId: Int, product: `Product`): Future[Either[ErrorWhileUpdatingProductDetails.type, Int]] = {
    productCollection.updateMany(new Document("productId", productId), new Document("$set", product), UpdateOptions().upsert(true)).toFuture().map(_ => Right(productId)).recover {
      case e: Exception => Left(ErrorWhileUpdatingProductDetails)
    }
  }

  def dropCollection(): Either[Exception, String] = {
    try {
      Await.result(categoryCollection.drop().toFuture(), 5 seconds)
      Await.result(productCollection.drop().toFuture(), 5 seconds)
      Right("ok")
    }
    catch {
      case e: Exception => Left(e)
    }
  }

  def findProduct(productId: Int): Future[Either[NoProductFoundError.type, Seq[`Product`]]] = {
    productCollection.find(equal("productId", productId)).toFuture().map(p => Right(p)).recover {
      case e: Exception => Left(NoProductFoundError)
    }
  }

  def findAllProduct: Future[Either[NoProductFoundError.type, Seq[`Product`]]] = {
    productCollection.find().toFuture().map(p => Right(p)).recover {
      case e: Exception => Left(NoProductFoundError)
    }
  }

}

