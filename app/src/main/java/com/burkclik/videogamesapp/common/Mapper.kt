package com.burkclik.videogamesapp.common

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}