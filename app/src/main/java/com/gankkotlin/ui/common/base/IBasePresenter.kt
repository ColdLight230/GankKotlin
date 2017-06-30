package com.gankkotlin.ui.common.base

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/8
 */
interface IBasePresenter {

    fun onAttach()
    fun onCreate()
    fun onViewCreated()
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroyView()
    fun onDestroy()
    fun onDetach()
}