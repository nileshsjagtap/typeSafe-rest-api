package core

import play.api.libs.json.Json

trait Entity

case class Category(
                     categoryId: Int,
                     parentId: Option[Int],
                     productIds: List[Int]
                   ) extends Entity

object Category {
  implicit val categoryFormats = Json.format[Category]
}

case class `Product`(
                      productId: Int,
                      productName: String,
                      price: Int
                    ) extends Entity

object `Product` {
  implicit val productFormats = Json.format[`Product`]
}
