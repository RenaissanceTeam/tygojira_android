package ru.fors.other.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.scopedBy
import org.koin.experimental.builder.singleBy
import ru.fors.other.presentation.view.fragment.OtherFragment
import ru.fors.other.presentation.viewmodel.OtherViewModel

/**
 * Created by 23alot on 24.02.2020.
 */
val otherModule = module {
    scope(named<OtherFragment>()) {
        viewModel { OtherViewModel(get()) }
    }
}