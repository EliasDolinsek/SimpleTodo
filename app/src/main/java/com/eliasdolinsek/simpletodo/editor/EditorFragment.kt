package com.eliasdolinsek.simpletodo.editor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eliasdolinsek.simpletodo.TodoItemApplication
import com.eliasdolinsek.simpletodo.databinding.FragmentEditorBinding
import com.eliasdolinsek.simpletodo.extensions.asFormattedDate
import com.eliasdolinsek.simpletodo.viewmodel.EditorViewModel
import com.eliasdolinsek.simpletodo.viewmodel.EditorViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class EditorFragment : Fragment() {

    private val args: EditorFragmentArgs by navArgs()
    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: FragmentEditorBinding
    private lateinit var adapter: ArrayAdapter<String>

    private var name: String = ""
    private var description: String = ""
    private var deadline: Calendar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditorBinding.inflate(layoutInflater, container, false)

        val factory =
            EditorViewModelFactory(
                args.id?.let { UUID.fromString(args.id) },
                (requireActivity().application as TodoItemApplication).repository
            )

        viewModel = ViewModelProvider(this, factory).get(EditorViewModel::class.java).also {
            it.todoItem.also { todoItem ->
                name = todoItem.name
                description = todoItem.description
                deadline = todoItem.deadline

                binding.imgbtnEditorDelete.setOnClickListener {
                    viewModel.removeTodoItem(todoItem.id)
                    findNavController().popBackStack()
                }
            }
        }


        viewModel.newTodoItem?.let {
            if (!it) {
                binding.imgbtnEditorDelete.visibility = View.VISIBLE
            }
        }

        binding.editTextEditorName.setText(name)
        binding.editTextEditorDescription.setText(description)

        updateDeadline()

        binding.imgbtnEditorClearDate.setOnClickListener {
            deadline = null
            updateDeadline()
        }

        binding.btnEditorDate.setOnClickListener {
            selectDate()
        }

        binding.btnEditorSave.setOnClickListener {
            setTodoItem()
            findNavController().popBackStack()
        }

        binding.btnEditorCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


    private fun setTodoItem() {
        name = binding.editTextEditorName.text.toString()
        description = binding.editTextEditorDescription.text.toString()
        viewModel.setTodoItem(name, deadline, description)
    }

    private fun selectDate() {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener { timeMillis ->
            deadline = Calendar.getInstance().apply { timeInMillis = timeMillis }
            updateDeadline()
        }

        picker.show(parentFragmentManager, picker.toString())
    }

    private fun updateDeadline() {
        binding.btnEditorDate.text = deadline?.asFormattedDate() ?: "SELECT"
        setBtnClearDateVisibility()
    }


    private fun setBtnClearDateVisibility() {
        if (deadline != null) {
            binding.imgbtnEditorClearDate.visibility = View.VISIBLE
        } else {
            binding.imgbtnEditorClearDate.visibility = View.GONE
        }
    }
}