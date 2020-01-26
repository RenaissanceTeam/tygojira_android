package ru.fors.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import ru.fors.R
import ru.fors.auth.di.authModule

/**
 * Created by 23alot on 26.01.2020.
 */
class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AppActivity", "started")
        setContentView(R.layout.activity_main)
    }
}