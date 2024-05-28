package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

// Adapter for the RecyclerView in MainActivity.kt
// This class takes a list of Todo objects and displays them in a RecyclerView.


class TodoAdapter(
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    // ViewHolder class for the RecyclerView items
    // This nested class defines the layout of each item in the RecyclerView.
    // It contains a TextView for the todo title and a CheckBox for the todo completion status.

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    // This method creates a new ViewHolder object for each item in the RecyclerView.
    // It inflates the layout for each item and returns a new TodoViewHolder object.
    // The TodoViewHolder object is then returned by the method.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }




    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }


    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
    }
        notifyDataSetChanged()
    }

    // This method toggles the strike-through effect on a TextView based on the isChecked parameter.
    // If the isChecked parameter is true, the strike-through effect is applied to the TextView.
    // If the isChecked parameter is false, the strike-through effect is removed from the TextView.

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if (isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    // This method binds the data from a Todo object to the ViewHolder's UI elements.
    // It sets the text of the TextView to the todo title and sets the CheckBox's isChecked property to the todo's isChecked property.

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.binding.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = isChecked
            }
        }
    }

    // This method returns the number of items in the todos list.
    override fun getItemCount(): Int {
        return todos.size
    }
}