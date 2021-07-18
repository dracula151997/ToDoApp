package com.semicolon.todoapp.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 *Created by Hassan Mohammed on 7/18/21
 */
fun Fragment.hideSoftKeyboard() {
    val im = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = requireActivity().currentFocus
    im.hideSoftInputFromWindow(view?.windowToken, 0)
}