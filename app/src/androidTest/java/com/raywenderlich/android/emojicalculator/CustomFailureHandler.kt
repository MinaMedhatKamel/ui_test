package com.raywenderlich.android.emojicalculator

import android.app.Instrumentation
import android.content.Context
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.base.DefaultFailureHandler
import org.hamcrest.Matcher

class CustomFailureHandler(@NonNull instrumentation: Instrumentation) : FailureHandler {
    private var delegate: FailureHandler

    init {
        delegate = DefaultFailureHandler(instrumentation.targetContext)
    }


    override fun handle(error: Throwable, viewMatcher: Matcher<View>) {
        try {
            Log.d("CustomFailureHandler", "handle: we are in the custom error handler")
            delegate.handle(error, viewMatcher)
        } catch (e: NoMatchingViewException) {
            throw MySpecialException(e)
        }

    }
}

class MySpecialException(e: NoMatchingViewException) : Throwable(e) {

}
