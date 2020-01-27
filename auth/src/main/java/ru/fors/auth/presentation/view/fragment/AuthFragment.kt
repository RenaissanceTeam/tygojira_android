package ru.fors.auth.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import ru.fors.R
import ru.fors.auth.presentation.viewmodel.*

/**
 * Created by 23alot on 26.01.2020.
 */
class AuthFragment : Fragment() {

    private val model: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            val login = loginET.text?.toString()
            val password = passwordET.text?.toString()
            model.onLoginRequired(login = login, password = password)
        }
        GlobalScope.launch {
            model.state
                .collect { updateState(it) }
        }
    }

    private fun updateState(state: AuthViewState) {
        when (state.event) {
            AuthEvent.SignInEvent -> showSignInState(state.signInState)
            AuthEvent.LoginEvent -> showLoginState(state.loginState)
            AuthEvent.PasswordEvent -> showPasswordState(state.passwordState)
            AuthEvent.Start -> Unit
        }
    }

    private fun showSignInState(state: SignInState) {
        when (state) {
            SignInState.Default -> showSignInDefault()
            is SignInState.Error -> showSignInError(state.throwable)
            SignInState.Success -> showSignInSuccess()
        }
    }

    private fun showSignInDefault() {

    }

    private fun showSignInError(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun showSignInSuccess() {
        // TODO: переход к следующему экрану
    }

    private fun showLoginState(state: LoginState) {
        when (state) {
            LoginState.Default -> showLoginDefault()
            LoginState.NotAllowed -> showLoginNotAllowed()
            LoginState.Short -> showLoginShort()
            LoginState.Empty -> showLoginEmpty()
        }
    }

    private fun showLoginDefault() {
        loginTextInput.isErrorEnabled = false
    }

    private fun showLoginNotAllowed() {
        loginTextInput.isErrorEnabled = true
        loginTextInput.error = "Недопустимый логин"
    }

    private fun showLoginShort() {
        loginTextInput.isErrorEnabled = true
        loginTextInput.error = "Короткий логин"
    }

    private fun showLoginEmpty() {
        loginTextInput.isErrorEnabled = true
        loginTextInput.error = "Пустой логин"
    }

    private fun showPasswordState(state: PasswordState) {
        when (state) {
            PasswordState.Default -> showPasswordDefault()
            PasswordState.NotAllowed -> showPasswordNotAllowed()
            PasswordState.Short -> showPasswordShort()
            PasswordState.Empty -> showPasswordEmpty()
        }
    }

    private fun showPasswordDefault() {
        loginTextInput.isErrorEnabled = false
    }

    private fun showPasswordNotAllowed() {
        loginTextInput.isErrorEnabled = true
        loginTextInput.error = "Недопустимый логин"
    }

    private fun showPasswordShort() {
        loginTextInput.isErrorEnabled = true
        loginTextInput.error = "Короткий логин"
    }

    private fun showPasswordEmpty() {
        loginTextInput.isErrorEnabled = true
        loginTextInput.error = "Пустой логин"
    }
}