package com.bornbytes.mydailysubscription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bornbytes.mydailysubscription.ui.auth.LoginFragment
import com.bornbytes.mydailysubscription.util.replaceFragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (savedInstanceState == null)
            supportFragmentManager.replaceFragment(R.id.container, LoginFragment.newInstance())

    }
}