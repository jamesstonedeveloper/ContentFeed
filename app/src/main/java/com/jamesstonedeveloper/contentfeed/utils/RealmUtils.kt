package com.jamesstonedeveloper.contentfeed.utils

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.Nullable
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.kotlin.delete

class RealmUtils {
    private var realm: Realm = Realm.getDefaultInstance()

    fun <realmObject: RealmObject> writeObjectToRealm(objectToWrite: realmObject, realmUtilsWriteCallback: RealmUtilsWriteCallback? = null) {
        realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.insertOrUpdate(objectToWrite)
        }
        realmUtilsWriteCallback?.onWriteComplete()
    }

    fun <realmObject: RealmObject> writeListToRealm(listToWrite: List<realmObject>) {
        realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.insertOrUpdate(listToWrite)
        }
    }

    fun <realmObject: RealmObject> getAllAsRealmList(clazz: Class<realmObject>): RealmResults<realmObject> {
        return realm.where(clazz).findAllAsync()
    }

    fun <realmObject: RealmObject> deleteObjectFromRealm(objectToDelete: realmObject) {
        realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            objectToDelete.deleteFromRealm()
        }
    }

    fun <realmObject: RealmObject> copyListFromRealm(listToCopy: List<realmObject>): List<realmObject> {
        Log.i(TAG, "copyListFromRealm: " + listToCopy.size)
        return realm.copyFromRealm(listToCopy)
    }

    interface RealmUtilsWriteCallback {
        fun onWriteComplete()
    }
}