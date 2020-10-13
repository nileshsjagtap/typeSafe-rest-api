package core

trait Error

final case class NoCategoryFoundError(categoryId: String) extends Error

final case class NoProductFoundError(productId: String) extends Error

case object ErrorWhileAddingCategory extends Error

case object ErrorWhileAddingProduct extends Error

case object ErrorWhileUpdatingProductDetails extends Error

case object UNABLE_TO_PARSE_JSON extends Error