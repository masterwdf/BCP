package com.example.data.entity

class UserEntity {

    var name: String? = null
    var address: String? = null

    constructor() {}

    constructor(name: String?, address: String?) {
        this.name = name
        this.address = address
    }
}