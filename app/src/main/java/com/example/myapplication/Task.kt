package com.example.myapplication

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

open class Task: RealmObject {
    @PrimaryKey
    var id : Long = 0
    var title: String = ""
    var detail: String = ""
    var date : Date = Date()
    var time : Date = Date()
    var notifyTime : Int = 0
    var limit: String? = null
}