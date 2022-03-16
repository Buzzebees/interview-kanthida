package com.example.buzzebeesassignment.data

import android.os.Parcel
import android.os.Parcelable
import com.example.buzzebeesassignment.model.BaseJsonObject

data class CampaignDao(var name: String? = ""
                       , var price: String? = ""
                       , var imageUrl: String? = ""
                       , var description: String? = "") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    constructor(json: BaseJsonObject) : this("") {
        name = json.getString("name", "")
        price = json.getString("price", "")
        imageUrl = json.getString("image_url", "")
        description = json.getString("description", "")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CampaignDao> {
        override fun createFromParcel(parcel: Parcel): CampaignDao {
            return CampaignDao(parcel)
        }

        override fun newArray(size: Int): Array<CampaignDao?> {
            return arrayOfNulls(size)
        }
    }

}