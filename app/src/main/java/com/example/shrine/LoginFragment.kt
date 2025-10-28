package com.example.shrine

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment.
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        val nextButton = view.findViewById<Button>(R.id.next_button)
        val passwordEditText = view.findViewById<EditText>(R.id.password_edit_text)
        val passwordTextInput = view.findViewById<TextInputLayout>(R.id.password_text_input)

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener {
            if (!isPasswordValid(passwordEditText.text!!)) {
                passwordTextInput.error = getString(R.string.shr_error_password)
            } else {
                // Clear the error.
                passwordTextInput.error = null
                // Navigate to the next Fragment.
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        }

        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener { _, keyCode: Int, event: KeyEvent ->
            if (isPasswordValid(passwordEditText.text!!)) {
                // Clear the error.
                passwordTextInput.error = null
            }
            false
        }

        return view
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}

