package com.peter.bosta_assessment.common.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

open class BaseFragment<VM: BaseViewModel>: Fragment() {
    lateinit var viewModel: VM

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClassType(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }

    private fun getViewModelForThisClass(): VM {
        return ViewModelProvider(this).get(getViewModelClassType())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModelForThisClass()

    }
}