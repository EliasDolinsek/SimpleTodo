package com.eliasdolinsek.simpletodo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliasdolinsek.simpletodo.databinding.TodoItemBinding
import com.eliasdolinsek.simpletodo.domain.TodoItem

class TodoItemAdapter(var items: List<TodoItem>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

}

class ViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(todoItem: TodoItem) {
        binding.apply {
            binding.name = todoItem.name
            binding.done = todoItem.done
        }
    }
}