package com.otex.homamuser.view.restaurantitem.model

import android.os.Parcel
import android.os.Parcelable

data class Addition(
    val id: String,
    val name: String,
    val price: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Addition> {
        override fun createFromParcel(parcel: Parcel): Addition {
            return Addition(parcel)
        }

        override fun newArray(size: Int): Array<Addition?> {
            return arrayOfNulls(size)
        }
    }
}