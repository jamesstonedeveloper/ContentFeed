package com.jamesstonedeveloper.contentfeed

import android.app.Application
import io.realm.DynamicRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration

class ContentFeedApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .schemaVersion(0)
            .migration(ContentFeedMigration())
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}

class ContentFeedMigration: RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

    }

}