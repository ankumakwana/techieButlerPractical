package com.example.techiebutlerpractical.koin

import com.example.techiebutlerpractical.viewmodels.PostsViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModule {
    val viewModule:Module = module {
        factory { PostsViewModel() }
    }
}