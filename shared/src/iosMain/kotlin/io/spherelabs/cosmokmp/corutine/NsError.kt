package io.spherelabs.cosmokmp.corutine

import kotlinx.cinterop.convert
import platform.Foundation.NSError
import platform.Foundation.NSLocalizedDescriptionKey
import platform.darwin.NSInteger
import kotlin.native.internal.ObjCErrorException

/**
 * Converts a [Throwable] to a [NSError].
 *
 * The returned [NSError] has `KotlinException` as the [NSError.domain], `0` as the [NSError.code] and
 * the [NSError.localizedDescription] is set to the [Throwable.message].
 *
 * The Kotlin throwable can be retrieved from the [NSError.userInfo] with the key `KotlinException`.
 */
fun Throwable.asNSError(): NSError {
    val userInfo = buildMap<Any?, Any> {
        this["KotlinException"] = this@asNSError
        message?.let {
            this[NSLocalizedDescriptionKey] = it
        }
    }
    return NSError.errorWithDomain(
        domain = "KotlinException",
        code = 0.convert(),
        userInfo = userInfo
    )
}

/**
 * Indicates if `this` [NSError] represents a [Throwable].
 */
val NSError.isKotlinThrowable: Boolean
    get() {
        return domain == "KotlinException" &&
            code == 0.convert<NSInteger>() &&
            userInfo["KotlinException"] is Throwable
    }

/**
 * Converts `this` [NSError] to a [Throwable].
 *
 * If `this` [NSError] represents a [Throwable], the original [Throwable] is returned.
 * For other [NSError]s an [ObjCErrorException] will be returned.
 *
 * @see asNSError
 */
fun NSError.asThrowable(): Throwable {
    return if (isKotlinThrowable) {
        userInfo["KotlinException"] as Throwable
    } else {
        ObjCErrorException(
            message = localizedDescription,
            error = this
        )
    }
}
