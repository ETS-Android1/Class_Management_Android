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

public class ClassroomNotificationAdapter extends ArrayAdapter<Classroom>
{
    private Context m_context;
    private int m_resource_id;
    private List<Classroom> m_list_classrooms; // monitor

    // constructor
    public ClassroomNotificationAdapter(@NonNull Context context, int resource, @NonNull List<Classroom> objects)
    {
        super(context, resource, objects);
        this.m_context = context;
        this.m_resource_id = resource;
        this.m_list_classrooms = objects;
    }

    // create a holder to hold view objects in the list view
    private class viewHolder
    {
        TextView tvInformationClass, tvIdAndNameClass, tvTimeClassStart, tvTimeClassEnd;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ClassroomNotificationAdapter.viewHolder holder;
        if(convertView == null || convertView.getTag() == null)
        {
            convertView = View.inflate(this.m_context,this.m_resource_id,null);
            holder = new ClassroomNotificationAdapter.viewHolder();
            holder.tvIdAndNameClass = (TextView) convertView.findViewById(R.id.tvIdAndNameClass);
            holder.tvTimeClassStart = (TextView) convertView.findViewById(R.id.tvTimeClassStart);
            holder.tvTimeClassEnd = (TextView) convertView.findViewById(R.id.tvTimeClassEnd);
            holder.tvInformationClass = (TextView) convertView.findViewById(R.id.tvInformationClass);
            convertView.setTag(holder);
        }
        else
            holder = (ClassroomNotificationAdapter.viewHolder) convertView.getTag();
        Classroom classroom = this.m_list_classrooms.get(position);
        holder.tvIdAndNameClass.setText(classroom.getId() +" - "+ classroom.getSubjectName());
        holder.tvTimeClassStart.setText(classroom.getStartTime());
        holder.tvTimeClassEnd.setText(classroom.getEndTime());
        holder.tvInformationClass.setText(classroom.getWeekDay().toString() +", " + "Classroom: " + classroom.getClassroomName());
        return convertView;
    }
}
