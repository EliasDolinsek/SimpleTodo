package com.eliasdolinsek.simpletodo.editor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.eliasdolinsek.simpletodo.TodoItemApplication
import com.eliasdolinsek.simpletodo.databinding.FragmentEditorBinding
import com.eliasdolinsek.simpletodo.viewmodel.EditorViewModel
import com.eliasdolinsek.simpletodo.viewmodel.EditorViewModelFactory
import com.eliasdolinsek.simpletodo.viewmodel.TodoItemViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class EditorFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val args: EditorFragmentArgs by navArgs()

    private val model: TodoItemViewModel by viewModels()
    private lateinit var viewModel: EditorViewModel

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEditorBinding =
            FragmentEditorBinding.inflate(layoutInflater, container, false)

        val factory =
            EditorViewModelFactory(
                UUID.fromString(args.id),
                (requireActivity().application as TodoItemApplication).repository
            )

        viewModel = ViewModelProvider(this, factory).get(EditorViewModel::class.java)

        viewModel.todoItem.also {
            binding.editTextEditorName.setText(it.name)
        }

        binding.spEditorDateSelection.onItemSelectedListener = this
        return binding.root
    }

    private fun temp() {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(parentFragmentManager, picker.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

}