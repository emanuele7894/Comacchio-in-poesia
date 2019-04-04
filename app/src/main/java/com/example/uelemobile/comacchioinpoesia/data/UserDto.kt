package com.example.uelemobile.comacchioinpoesia.data


class UserDto {
    var item: String = ""
    var titolo: String = ""
    var sottotitolo: String = ""

    constructor(){}

    constructor(item: String, titolo: String, sottotitolo: String) {

        this.item = item
        this.titolo = titolo
        this.sottotitolo = sottotitolo
    }
}