package com.example.cineapp.services.realmDB

import android.annotation.SuppressLint
import androidx.multidex.MultiDexApplication
import com.example.cineapp.services.objects.account.Account
import com.example.cineapp.services.objects.account.Avatar
import com.example.cineapp.services.objects.account.AvatarPath
import com.example.cineapp.services.objects.account.HashAvatar
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

/**
 * @author Hayrum Ivan
 * @since 09/10/2022
 * @description Class for create/update Instance of the RealmDB and init the custom Application app.
 */
class RealmConfig : MultiDexApplication(), Thread.UncaughtExceptionHandler {

    companion object {
        lateinit var config: RealmConfiguration
        private var ueh: Thread.UncaughtExceptionHandler? = null

        /**
         * Create instance of the Realm.
         */
        fun getInstanceRealm(): Realm {
            return try {
                config = RealmConfiguration.Builder(
                    schema =
                    setOf(
                        Account::class,
                        Avatar::class,
                        HashAvatar::class,
                        AvatarPath::class
                    )
                )
                    .name("movies.db")
                    .deleteRealmIfMigrationNeeded()
                    .schemaVersion(0)
                    .build()
                Realm.open(config)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    @SuppressLint("NotConstructor")
    fun RealmConfig() {
        ueh = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        ueh!!.uncaughtException(t, e)
    }

}