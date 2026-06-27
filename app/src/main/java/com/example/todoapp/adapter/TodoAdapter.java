//package com.example.todoapp.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.todoapp.AppDatabase;
//import com.example.todoapp.EditTodoActivity;
//import com.example.todoapp.R;
//import com.example.todoapp.Todo;
//
//import java.util.List;
//
//public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
//
//    private Context context;
//    private List<Todo> todoList;
//    private AppDatabase db;
//
//    public TodoAdapter(Context context, List<Todo> todoList, AppDatabase db) {
//        this.context = context;
//        this.todoList = todoList;
//        this.db = db;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(context)
//                .inflate(R.layout.item_todo, parent, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        Todo todo = todoList.get(position);
//
//        holder.txtTitle.setText(todo.title);
//        holder.txtDescription.setText(todo.description);
//
//        holder.btnDelete.setOnClickListener(v -> {
//
//            db.todoDao().delete(todo);
//
//            todoList.remove(position);
//
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(position, todoList.size());
//
//        });
//
//        holder.btnEdit.setOnClickListener(v -> {
//
//            Intent intent = new Intent(context, EditTodoActivity.class);
//
//            intent.putExtra("todoId", todo.id);
//
//            context.startActivity(intent);
//
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return todoList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView txtTitle, txtDescription;
//        Button btnEdit, btnDelete;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            txtTitle = itemView.findViewById(R.id.txtTitle);
//            txtDescription = itemView.findViewById(R.id.txtDescription);
//
//            btnEdit = itemView.findViewById(R.id.btnEdit);
//            btnDelete = itemView.findViewById(R.id.btnDelete);
//        }
//    }
//}


package com.example.todoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
        holder.txtDueDate.setText("📅 " + todo.dueDate);
        holder.txtTags.setText("🏷 " + todo.tags);
        holder.txtPriority.setText("Priority : " + todo.priority);

        holder.chkCompleted.setOnCheckedChangeListener(null);
        holder.chkCompleted.setChecked(todo.completed);

        if (todo.completed) {

            holder.txtTitle.setPaintFlags(
                    holder.txtTitle.getPaintFlags()
                            | Paint.STRIKE_THRU_TEXT_FLAG);

            holder.txtTitle.setAlpha(0.5f);

        } else {

            holder.txtTitle.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
            holder.txtTitle.setAlpha(1f);

        }

        holder.chkCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {

            todo.completed = isChecked;
            todo.updatedAt = System.currentTimeMillis();

            db.todoDao().update(todo);

        });

//        holder.btnDelete.setOnClickListener(v -> {
//
//            db.todoDao().delete(todo);
//
//            todoList.remove(position);
//
//            notifyItemRemoved(position);
//
//        });

        holder.btnDelete.setOnClickListener(v -> {

            new androidx.appcompat.app.AlertDialog.Builder(context)
                    .setTitle("Delete Todo")
                    .setMessage("Are you sure you want to delete this todo?")
                    .setPositiveButton("Delete", (dialog, which) -> {

                        db.todoDao().delete(todo);

                        todoList.remove(position);

                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, todoList.size());

                        dialog.dismiss();

                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();

        });

        holder.btnEdit.setOnClickListener(v -> {

            Intent intent =
                    new Intent(context, EditTodoActivity.class);

            intent.putExtra("todoId", todo.id);

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtDescription;
        TextView txtDueDate;
        TextView txtTags;
        TextView txtPriority;

        CheckBox chkCompleted;

        Button btnEdit;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtDueDate = itemView.findViewById(R.id.txtDueDate);
            txtTags = itemView.findViewById(R.id.txtTags);
            txtPriority = itemView.findViewById(R.id.txtPriority);

            chkCompleted = itemView.findViewById(R.id.chkCompleted);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}