package com.example.facebook.model

import android.os.Parcel
import android.os.Parcelable

class Post() : Parcelable {
    var profile:Int = 0
    var fullname:String = ""
    var link:String = ""
    var title:String = ""
    var link_domain:String = ""
    var photo:Int = 0
    lateinit var description:String
    var photo_2:Int = 0
    var photo_3:Int = 0
    var photo_4:Int = 0
    var photo_5:Int = 0
    var isProfile:Boolean =false
    var isMultiple:Boolean =false
    var isLink:Boolean =false

    constructor(parcel: Parcel) : this() {
        profile = parcel.readInt()
        fullname = parcel.readString().toString()
        link = parcel.readString().toString()
        title = parcel.readString().toString()
        link_domain = parcel.readString().toString()
        photo = parcel.readInt()
        description = parcel.readString().toString()
        photo_2 = parcel.readInt()
        photo_3 = parcel.readInt()
        photo_4 = parcel.readInt()
        photo_5 = parcel.readInt()
        isProfile = parcel.readByte() != 0.toByte()
        isMultiple = parcel.readByte() != 0.toByte()
        isLink = parcel.readByte() != 0.toByte()
    }

    constructor( profile:Int,  fullname:String, photo:Int, isProfile:Boolean =false) : this() {
        this.profile = profile
        this.fullname = fullname
        this.photo = photo
        this.isProfile = isProfile

    }
//
    constructor( profile:Int,  fullname:String, photo:Int, photo_2:Int, photo_3:Int,photo_4:Int, photo_5:Int,isProfile:Boolean =false, isMultiple:Boolean =false) : this() {
        this.profile = profile
        this.fullname = fullname
        this.photo = photo
        this.photo_2 = photo_2
        this.photo_3 = photo_3
        this.photo_4 = photo_4
        this.photo_5 = photo_5
        this.isProfile = isProfile
        this.isMultiple = isMultiple

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(profile)
        parcel.writeString(fullname)
        parcel.writeInt(photo)
        parcel.writeString(description)
        parcel.writeInt(photo_2)
        parcel.writeInt(photo_3)
        parcel.writeInt(photo_4)
        parcel.writeInt(photo_5)
        parcel.writeByte(if (isProfile) 1 else 0)
        parcel.writeByte(if (isMultiple) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}