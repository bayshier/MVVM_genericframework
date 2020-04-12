package com.base.timber

/** Not a real crash reporting library!  */
class FakeCrashLibrary private constructor() {
    companion object {
        fun log(priority: Int, tag: String?, message: String?) { // TODO add log entry to circular buffer.
        }

        fun logWarning(t: Throwable?) { // TODO report non-fatal warning.
        }

        fun logError(t: Throwable?) { // TODO report non-fatal error.
        }
    }

    init {
        throw AssertionError("No instances.")
    }
}