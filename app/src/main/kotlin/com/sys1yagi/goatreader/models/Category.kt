package com.sys1yagi.goatreader.models

import com.activeandroid.annotation.Table
import com.activeandroid.Model
import com.activeandroid.annotation.Column
import java.util.Date

Table(name = Category.TABLE_NAME)
public class Category() : Model() {
    class object {
        val TABLE_NAME = "Categories"
        val NAME = "name"
        val CREATED_AT = "created_at"
        val UPDATED_AT = "updated_at"

        fun create(name: String): Category {
            val category = Category()
            category.name = name
            return category
        }
    }

    Column(name = NAME)
    var name: String? = null

    Column(name = CREATED_AT)
    var createdAt: Date? = null

    Column(name = UPDATED_AT)
    var updatedAt: Date? = null
}
