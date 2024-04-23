package com.rn.livros.model

import org.parceler.Parcel

@Parcel
class Publisher(
    var id:String = "",
    var name: String = ""
) {
    override fun toString(): String = "$id - $name"
}