package com.jamesstonedeveloper.contentfeed.data.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Post(@PrimaryKey var id: String? = "", var title: String? = "", var body: String? = "", var createdAt: Long? = Date().time, var updatedAt: Long? = 0): RealmObject() {
}