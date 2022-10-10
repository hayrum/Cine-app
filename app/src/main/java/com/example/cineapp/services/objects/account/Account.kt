package com.example.cineapp.services.objects.account

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmObject
import java.io.Serializable

class Account : RealmObject, Serializable {
    @SerializedName("id")
    var idAccount: Long = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("username")
    var userName: String? = null

    @SerializedName("iso_639_1")
    var iso639: String? = null

    @SerializedName("iso_3166_1")
    var iso3166: String? = null

    @SerializedName("include_adult")
    var isPermissionWithMoviesOfAdult: Boolean = false

    @SerializedName("avatar")
    var avatar: Avatar? = null
}

class Avatar : RealmObject, Serializable {
    @SerializedName("gravatar")
    var gravatar: HashAvatar? = null

    @SerializedName("tmdb")
    var avatarUrl: AvatarPath? = null
}

class HashAvatar : RealmObject, Serializable {
    @SerializedName("hash")
    var hashAvatar: String? = null
}


class AvatarPath : RealmObject, Serializable {
    @SerializedName("avatar_path")
    var avatarPath: String? = null
}
