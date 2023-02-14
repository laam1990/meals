package com.example.yape.ui.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun EditText.textChanges(): Flow<CharSequence> {
    return callbackFlow {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // este método se llama cuando el texto está cambiando
                s?.let {
                    trySend(it)
                } // emitimos el valor al Flow
            }
        }
        addTextChangedListener(textWatcher)
        awaitClose {
            removeTextChangedListener(textWatcher)
        }
    }
}