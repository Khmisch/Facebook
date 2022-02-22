package com.example.facebook.model

import android.icu.text.CaseMap
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

 class Link :Serializable{
     var img:String? = null
     var title:String? = null
     var domain:String? = null


     override fun toString(): String {
         return img!!
     }
}