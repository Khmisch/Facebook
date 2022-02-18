package com.example.facebook.model

class Post{
    var profile:Int = 0
    var fullname:String = ""
    var photo:Int = 0
    var photo_2:Int = 0
    var photo_3:Int = 0
    var photo_4:Int = 0
    var photo_5:Int = 0
    var isProfile:Boolean =false
    var isMultiple:Boolean =false

    constructor( profile:Int,  fullname:String, photo:Int, isProfile:Boolean =false){
        this.profile = profile
        this.fullname = fullname
        this.photo = photo
        this.isProfile = isProfile

    }
    constructor( profile:Int,  fullname:String, photo:Int, photo_2:Int, photo_3:Int,photo_4:Int, photo_5:Int,isProfile:Boolean =false, isMultiple:Boolean =false){
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
}