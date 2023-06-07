package com.example.myapplication.roomdatabase.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
class FavoriteData(
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "price")
    var price: String?,
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String?,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,
    @ColumnInfo(name = "rating")
    var rating: Double,
    @PrimaryKey()
    @ColumnInfo(name = "id")
    var id: Int) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<FavoriteData> {
        override fun createFromParcel(parcel: Parcel): FavoriteData {
            return FavoriteData(parcel)
        }

        override fun newArray(size: Int): Array<FavoriteData?> {
            return arrayOfNulls(size)
        }
    }
}