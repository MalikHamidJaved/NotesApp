package com.example.noteapp.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.noteapp.repository.NotesRepository
import org.koin.java.KoinJavaComponent.inject

/**
 * BaseViewModel.kt
 * An abstract Base View model to share common behavior and additionally clear navigation reference upon invalidation.
 * @author Malik Dawar, malikdawar@hotmail.com
 */
abstract class BaseViewModel<View> : ViewModel() {
    private var view: View? = null
    private var lifecycleOwner: LifecycleOwner? = null

    protected val notesRepository: NotesRepository by inject(
        NotesRepository::class.java
    )

    /**
     * This method must be called by the UI to attach navigation to be monitored by the substituted view model to respond to UI specific event changes.
     */
    open fun attachView(view: View) {
        this.view = view
    }

    /**
     * @returns current navigator if attached
     * @throws NullPointerException if not attached.
     */
    protected fun getView(): View {
        if (view == null)
            throw NullPointerException("View is null please call attach method 1st")

        return view!!
    }

    /**
     * This method must be called by the View if the observer is required in the ViewModel
     * @param lifecycleOwner reference
     */
    open fun attachObserver(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    /**
     * @returns Observer if attached
     * @throws NullPointerException if not.
     */
    protected fun getLifecycleOwner(): LifecycleOwner {
        if (lifecycleOwner == null)
            throw NullPointerException("LifecycleOwner is null please attach lifecycleOwner")

        return lifecycleOwner!!
    }

    override fun onCleared() {
        view = null
    }
}