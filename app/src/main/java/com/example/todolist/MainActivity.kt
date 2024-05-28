package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //View binding is used to access the views in the layout file
    //without having to write findViewById() every time
    //in the code
    //so in less words it bind the views in the layout file to the variables in the code
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    //OnCreate Method is the entry point of the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TodoAdapter is used to create the list of todos
        todoAdapter = TodoAdapter(mutableListOf())
        //TodoAdapter is set to the RecyclerView rvTodoItems
        binding.rvTodoItems.adapter = todoAdapter
        //LinearLayoutManager is used to set the layout of the RecyclerView
        //to a linear layout
        //so that the items are displayed in a vertical manner
        //and not in a horizontal manner
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        //Add button click listener
        //This listener is used to add a new todo item to the list
        //when the user clicks the Add button
        //and the todo title is not empty
        //the todo is added to the list
        //and the todo title is cleared
        //so that the user can add more todos
        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTittle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodoTittle.text.clear()
            }
        }
        //Delete button click listener
        //This listener is used to delete all the done todos from the list
        //when the user clicks the Delete button
        binding.btnDeleteTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}
