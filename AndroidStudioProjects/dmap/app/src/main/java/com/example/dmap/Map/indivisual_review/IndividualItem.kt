package com.example.dmap.Map.indivisual_review

data class IndividualItem constructor(val userProfileUrl : String ="123",
val toiletRating : Float =3.5f, val userId : String="testId" , val toiletReviewText : String = "testText" ,
                                      val toiletImageUrl : String = "testUrl 1" , val toiletImageUrl2 : String = "testUrl2") {
}