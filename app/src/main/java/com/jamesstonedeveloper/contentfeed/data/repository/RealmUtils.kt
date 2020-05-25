package com.jamesstonedeveloper.contentfeed.data.repository

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

class RealmUtils {
    private var realm: Realm = Realm.getDefaultInstance()

    fun <realmObject: RealmObject> writeObjectToRealm(objectToWrite: realmObject) {
        realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.insertOrUpdate(objectToWrite)
        }
    }

    fun <realmObject: RealmObject> getAllAsRealmList(clazz: Class<realmObject>): RealmResults<realmObject> {
        return realm.where(clazz).findAllAsync()
    }
}