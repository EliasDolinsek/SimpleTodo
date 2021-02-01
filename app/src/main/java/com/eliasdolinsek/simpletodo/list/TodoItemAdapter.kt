package com.eliasdolinsek.simpletodo.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliasdolinsek.simpletodo.databinding.TodoItemBinding
import com.eliasdolinsek.simpletodo.domain.TodoItem
import java.util.*

class TodoItemAdapter(
    var items: List<TodoItem>,
    val onDoneChanged: (id: UUID) -> Unit,
    val onEdit: (id: UUID) -> Unit
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
        holder.binding.cbDone.setOnClickListener { onDoneChanged(item.id) }
        holder.binding.btnEdit.setOnClickListener { onEdit(item.id) }
        holder.bind(item)
    }

}

class ViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(todoItem: TodoItem) {
        binding.apply {
            binding.name = todoItem.name
            binding.done = todoItem.done
            binding.txtDeadline.text = todoItem.deadline.toString()
        }
    }
}