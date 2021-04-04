package com.otex.homamuser.view.restaurantitem.model

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val additions: List<Addition>,
    val description: String,
    val id: Int,
    val name: String,
    val options: List<Option>
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(Addition)!!,
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!,
            parcel.createTypedArrayList(Option)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(additions)
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeTypedList(options)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}