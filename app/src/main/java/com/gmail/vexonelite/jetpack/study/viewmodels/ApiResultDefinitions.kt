package com.gmail.vexonelite.jetpack.study.viewmodels


/**
 * * Ref: [Android Networking in 2019 — Retrofit with Kotlin’s Coroutines](https://android.jlelse.eu/android-networking-in-2019-retrofit-with-kotlins-coroutines-aefe82c4d777)
 */
sealed class FmApiResult<T> {
    data class Success<T>(val data: T) : FmApiResult<T>()
    data class Error<T>(val cause: Exception) : FmApiResult<T>()
}


/**
 * * Ref: [Railway Oriented Programming in Kotlin](https://proandroiddev.com/railway-oriented-programming-in-kotlin-f1bceed399e5)
 */
infix fun <T, R> FmApiResult<T>.then(block: (T) -> FmApiResult<R>) =
    when (this) {
        is FmApiResult.Success -> block(this.data)
        is FmApiResult.Error -> FmApiResult.Error(this.cause)
    }


infix fun <T, R> FmApiResult<T>.then(converter: IMapFunction<T, FmApiResult<R>>) =
    when (this) {
        is FmApiResult.Success -> converter.convertIntoData(this.data)
        is FmApiResult.Error -> FmApiResult.Error(this.cause)
    }



open class FmRuntimeException: RuntimeException {

    val exceptionCode: String

    constructor(message: String, exceptionCode: String): super(message) {
        this.exceptionCode = exceptionCode
    }

    constructor(cause: Throwable, exceptionCode: String): super(cause) {
        this.exceptionCode = exceptionCode
    }

    constructor(message: String, cause: Throwable, exceptionCode: String): super(message, cause) {
        this.exceptionCode = exceptionCode
    }
}


fun interface IMapFunction<in T, out R> {
    /**
     * Convert the input: T into the output:R.
     */
    @Throws(FmRuntimeException::class)
    fun convertIntoData(input: T): R
}

