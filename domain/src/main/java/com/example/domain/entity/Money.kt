package com.example.domain.entity

import android.os.Parcel
import android.os.Parcelable

class Money() : Parcelable {

    var country: String? = null
    var symbol: String? = null
    var image: String? = null
    var currencies: List<Currency?>? = null

    constructor(parcel: Parcel) : this() {
        country = parcel.readString()
        symbol = parcel.readString()
        image = parcel.readString()
        currencies = parcel.createTypedArrayList(Currency)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeString(symbol)
        parcel.writeString(image)
        parcel.writeTypedList(currencies)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Money> {
        override fun createFromParcel(parcel: Parcel): Money {
            return Money(parcel)
        }

        override fun newArray(size: Int): Array<Money?> {
            return arrayOfNulls(size)
        }
    }
}