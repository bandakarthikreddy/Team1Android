package com.singlepointsol.policy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.singlepointsol.policy.ui.theme.PolicyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize ViewModels
        val policyViewModel = ViewModelProvider(this)[PolicyViewModel::class.java]

        setContent {
            PolicyTheme  {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { paddingValues ->
                        PolicyPage(
                            modifier = Modifier
                                .padding(paddingValues)
                                .systemBarsPadding(),
                            viewModel = policyViewModel
                        )
                    }
                )
            }
        }
    }
}
