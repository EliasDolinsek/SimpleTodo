package com.eliasdolinsek.simpletodo.editor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.eliasdolinsek.simpletodo.R
import com.google.android.material.datepicker.MaterialDatePicker

class EditorFragment : Fragment() {

    private val args: EditorFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(parentFragmentManager, picker.toString())
        return inflater.inflate(R.layout.fragment_editor, container, false)
    }

}