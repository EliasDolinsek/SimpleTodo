package com.eliasdolinsek.simpletodo.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliasdolinsek.simpletodo.databinding.TodoItemBinding
import com.eliasdolinsek.simpletodo.domain.TodoItem
import com.eliasdolinsek.simpletodo.extensions.asFormattedDate
import java.util.*

class TodoItemAdapter(
    val onDoneChanged: (id: UUID) -> Unit,
    val onEdit: (id: UUID) -> Unit,
    var items: List<TodoItem> = listOf()
) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.cbTodoDone.setOnClickListener { onDoneChanged(item.id) }
        holder.binding.constraintLayoutTodoItem.setOnClickListener { onEdit(item.id) }
        holder.bind(item)
    }

}

class ViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(todoItem: TodoItem) {
        binding.apply {
            binding.name = if (todoItem.name.isEmpty()) {
                "No title"
            } else {
                todoItem.name
            }

            binding.done = todoItem.done
            binding.deadline = todoItem.deadline?.asFormattedDate() ?: "SELECT"

            if (todoItem.description.isNotEmpty()) {
                binding.description = todoItem.description
            } else {
                binding.tvTodoDescription.visibility = View.GONE
            }
        }
    }
}