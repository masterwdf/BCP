package com.example.domain.entity

import android.os.Parcel
import android.os.Parcelable

class Currency() : Parcelable {

    var flag: String? = null
    var rate: Double? = null
    var buy: Double? = null
    var sell: Double? = null

    constructor(parcel: Parcel) : this() {
        flag = parcel.readString()
        rate = parcel.readValue(Double::class.java.classLoader) as? Double
        buy = parcel.readValue(Double::class.java.classLoader) as? Double
        sell = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(flag)
        parcel.writeValue(rate)
        parcel.writeValue(buy)
        parcel.writeValue(sell)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currency> {
        override fun createFromParcel(parcel: Parcel): Currency {
            return Currency(parcel)
        }

        override fun newArray(size: Int): Array<Currency?> {
            return arrayOfNulls(size)
        }
    }
}