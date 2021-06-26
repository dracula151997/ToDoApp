package com.semicolon.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 *Created by Hassan Mohammed on 6/25/21
 */
abstract class BaseFragment<VB : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
    Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(setOptionMenu())
    }

    abstract fun setOptionMenu(): Boolean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
        setListenersForViews()
        subscribeLiveDataObservers()
    }

    abstract fun onViewCreated()
    abstract fun setListenersForViews()
    abstract fun subscribeLiveDataObservers()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}