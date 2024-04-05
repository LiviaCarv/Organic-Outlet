package com.project.organicoutlet.dao


import com.project.organicoutlet.database.Product
import java.math.BigDecimal

class ProductsDao {
    companion object {
        private val products =
            mutableListOf<Product>(
//                Product(
//                    "Salada de frutas",
//                    "Laranja, maçãs e uva",
//                    BigDecimal("19.83")
//                ),
//                Product(
//                    "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.",
//                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//                    BigDecimal("1234"),
//                    "invalida"
//                ),
//                Product(
//                    "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.",
//                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec ultrices tincidunt arcu non sodales neque. Pulvinar elementum integer enim neque volutpat ac. Vitae tempus quam pellentesque nec nam. Habitant morbi tristique senectus et netus et malesuada. Semper quis lectus nulla at. Sed enim ut sem viverra. Sed lectus vestibulum mattis ullamcorper velit sed ullamcorper morbi tincidunt. Faucibus vitae aliquet nec ullamcorper sit amet risus nullam. Rhoncus urna neque viverra justo nec ultrices dui. Amet massa vitae tortor condimentum lacinia quis vel eros donec. Diam quis enim lobortis scelerisque fermentum dui faucibus in ornare. Eget velit aliquet sagittis id consectetur. Neque vitae tempus quam pellentesque nec nam aliquam. Scelerisque fermentum dui faucibus in ornare quam viverra. Pellentesque dignissim enim sit amet venenatis urna cursus eget nunc. Mattis nunc sed blandit libero volutpat sed cras ornare arcu. Est ante in nibh mauris. Morbi enim nunc faucibus a. Ut faucibus pulvinar elementum integer enim.",
//                    BigDecimal("542922"),
//                    "https://images.pexels.com/photos/46174/strawberries-berries-fruit-freshness-46174.jpeg"
//                ),

                )
    }

    fun insert(product: Product) {
        products.add(product)
    }

    fun getAllProducts(): List<Product> {
        return products.toList()
    }
}