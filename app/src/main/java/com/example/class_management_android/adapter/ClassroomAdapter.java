package com.example.class_management_android.adapter;

import com.example.class_management_android.R;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.class_management_android.model.Classroom;

import java.util.List;

public class ClassroomAdapter extends ArrayAdapter<Classroom>
{
    private Context m_context;
    private int m_resource_id;
    private List<Classroom> m_list_classrooms; // monitor

    // constructor
    public ClassroomAdapter(@NonNull Context context, int resource, @NonNull List<Classroom> objects)
    {
        super(context, resource, objects);
        this.m_context = context;
        this.m_resource_id = resource;
        this.m_list_classrooms = objects;
    }

    // create a holder to hold view objects in the list view
    private class viewHolder
    {
        TextView tvOrderClass, tvIdClass, tvNameClass, tvTimeClass, tvRoomClass;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ClassroomAdapter.viewHolder holder;
        if(convertView == null || convertView.getTag() == null)
        {
            convertView = View.inflate(this.m_context,this.m_resource_id,null);
            holder = new ClassroomAdapter.viewHolder();
            holder.tvOrderClass = (TextView) convertView.findViewById(R.id.tvOrderClass);
            holder.tvIdClass = (TextView) convertView.findViewById(R.id.tvIdClass);
            holder.tvNameClass = (TextView) convertView.findViewById(R.id.tvNameClass);
            holder.tvTimeClass = (TextView) convertView.findViewById(R.id.tvTimeClass);
            holder.tvRoomClass = (TextView) convertView.findViewById(R.id.tvRoomClass);
            convertView.setTag(holder);
        }
        else
            holder = (ClassroomAdapter.viewHolder) convertView.getTag();
        Classroom classroom = this.m_list_classrooms.get(position);
        holder.tvOrderClass.setText(String.valueOf(position + 1));
        holder.tvIdClass.setText(classroom.getId());
        holder.tvNameClass.setText(classroom.getSubjectName());
        holder.tvTimeClass.setText(classroom.getStartTime()+ '-' + classroom.getEndTime());
        holder.tvRoomClass.setText(classroom.getClassroomName());
        return convertView;
    }
}
