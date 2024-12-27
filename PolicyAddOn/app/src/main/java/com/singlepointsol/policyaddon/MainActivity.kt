package com.singlepointsol.policyaddon

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
import com.singlepointsol.policyaddon.ui.theme.PolicyAddOnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val policyAddOnViewModel = ViewModelProvider(this)[PolicyAddOnViewModel::class.java]

        setContent {
            PolicyAddOnTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { paddingValues ->
                        PolicyAddOnPage(
                            modifier = Modifier
                                .padding(paddingValues)
                                .systemBarsPadding(),
                            viewModel = PolicyAddOnViewModel()
                        )
                    }
                )
            }
        }
    }
}
