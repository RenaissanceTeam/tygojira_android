package ru.fors.auth.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import ru.fors.R
import ru.fors.auth.presentation.viewmodel.AuthViewModel

/**
 * Created by 23alot on 26.01.2020.
 */
class AuthFragment : Fragment() {

    val model: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            val login = view.findViewById<TextInputEditText>(R.id.loginET).text?.toString()
            val password = view.findViewById<TextInputEditText>(R.id.passwordET).text?.toString()
            model.onLoginRequired(login = login, password = password)
            model.onTest()
        }
        GlobalScope.launch {
            model.state.collect { Log.d("AuthFragment", "$it") }
        }
    }
}