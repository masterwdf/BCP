package com.example.domain.entity

class User {

    var name: String? = null
    var address: String? = null

    constructor() {}

    constructor(name: String?, address: String?) {
        this.name = name
        this.address = address
    }
}