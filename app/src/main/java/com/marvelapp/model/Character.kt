package com.marvelapp.model

import com.google.gson.annotations.SerializedName



data class CharactersResponse(

    @SerializedName("code") val code : Int,
    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("attributionText") val attributionText : String,
    @SerializedName("attributionHTML") val attributionHTML : String,
    @SerializedName("etag") val etag : String,
    @SerializedName("data") val data : Data
    )
data class Data(
    @SerializedName("offset") val offset : Int,
    @SerializedName("limit") val limit : Int,
    @SerializedName("total") val total : Int,
    @SerializedName("count") val count : Int,
    @SerializedName("results") val characters : List<Character>
)
data class Character (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("modified") val modified : String,
    @SerializedName("thumbnail") val thumbnail : Thumbnail,
    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("comics") val comics : Comics,
    @SerializedName("series") val series : Series,
    @SerializedName("stories") val stories : Stories,
    @SerializedName("events") val events : Events,
    @SerializedName("urls") val urls : List<Urls>
)

data class Comics (

    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI : String,
    @SerializedName("items") val items : List<Items>,
    @SerializedName("returned") val returned : Int
)

data class Series (

    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI : String,
    @SerializedName("items") val items : List<Items>,
    @SerializedName("returned") val returned : Int
)

data class Stories (

    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI : String,
    @SerializedName("items") val items : List<Items>,
    @SerializedName("returned") val returned : Int
)

data class Urls (

    @SerializedName("type") val type : String,
    @SerializedName("url") val url : String
)

data class Events (

    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI : String,
    @SerializedName("items") val items : List<Items>,
    @SerializedName("returned") val returned : Int
)
data class Items (

    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("name") val name : String
)
data class Thumbnail (

    @SerializedName("path") val path : String,
    @SerializedName("extension") val extension : String
)