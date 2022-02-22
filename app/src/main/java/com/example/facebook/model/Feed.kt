package com.example.facebook.model

class Feed {
    var isHeader:Boolean = false
    var post:Post? =null
    var link:Link? =null
    var stories:ArrayList<Story> = ArrayList<Story>()

    constructor(){
        this.isHeader = true
    }
    constructor(post: Post){
        this.post= post
        this.isHeader = false
    }
    constructor(link:Link){
        this.link= link
        this.isHeader = false
    }
    constructor(stories: ArrayList<Story>){
        this.stories = stories
        this.isHeader = false
    }
}