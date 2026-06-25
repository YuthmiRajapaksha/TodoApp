package com.example.todoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.AppDatabase;
import com.example.todoapp.EditTodoActivity;
import com.example.todoapp.R;
import com.example.todoapp.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Context context;
    private List<Todo> todoList;
    private AppDatabase db;

    public TodoAdapter(Context context, List<Todo> todoList, AppDatabase db) {
        this.context = context;
        this.todoList = todoList;
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_todo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Todo todo = todoList.get(position);

        holder.txtTitle.setText(todo.title);
        holder.txtDescription.setText(todo.description);

        holder.btnDelete.setOnClickListener(v -> {

            db.todoDao().delete(todo);

            todoList.remove(position);

            notifyItemRemoved(position);
            notifyItemRangeChanged(position, todoList.size());

        });

        holder.btnEdit.setOnClickListener(v -> {

            Intent intent = new Intent(context, EditTodoActivity.class);

            intent.putExtra("todoId", todo.id);

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDescription;
        Button btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}