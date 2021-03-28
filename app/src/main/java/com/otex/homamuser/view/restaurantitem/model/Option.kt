package com.otex.homamuser.view.restaurantitem.model

import android.annotation.SuppressLint
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class Option(
        val feature_id: Int,
        val id: String,
        val name: String,
        val price: Int,
        var isChecked:Boolean=false
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(feature_id)
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Option> {
        override fun createFromParcel(parcel: Parcel): Option {
            return Option(parcel)
        }

        override fun newArray(size: Int): Array<Option?> {
            return arrayOfNulls(size)
        }
    }
}
