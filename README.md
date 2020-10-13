The task is building a Type-Safe REST service from scratch using Scala with
http4s and Cats-IO.
The Entities are “Categories” and “Products”.
Categories can have multiple and multilevel child categories.
Categories can have multiple products and a product can belong to multiple
categories.
The Entities must get saved in a database and be retrieved via POST and GET
Methods respectively.
Also, the candidate must be aware of HTTP Verbs and their significance in
RESTful APIs.

You need to design a proper data model and create APIs to
1. Add a category - as both parent and child.
2. Get all categories with all its child categories(at all sub levels) mapped
to it.
3. Add Product under any category(at any level) or categories.
4. Get all products by a category.
5. Update product details (name,price,etc).

routes -
POST     /category             addCategory  
GET      /categories             getCategories
GET      /getProducts/:categoryId  getProducts
POST     /product                addProduct
PUT      /updateProduct           UpdateProduct
