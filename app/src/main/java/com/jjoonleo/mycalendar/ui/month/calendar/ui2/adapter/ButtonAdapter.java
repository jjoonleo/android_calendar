package com.jjoonleo.mycalendar.ui.month.calendar.ui2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoonleo.mycalendar.MainActivity;
import com.jjoonleo.mycalendar.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ButtonAdapter extends RecyclerView.Adapter {
    Context context;
    MainActivity mainActivity = new MainActivity();

    public ButtonAdapter(Context context){
        this.context = context;

        //gregorianCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        protected Button button;
        public ViewHolder(View itemView){
            super(itemView);
            button = itemView.findViewById(R.id.Date_start);
            button.setTextSize(10);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                //Toast.makeText(context, "daskjdflaskjf", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(context, Main2Activity.class);
                    //intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    //context.startActivity(intent);
                }
            });
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        viewHolder.itemView.getLayoutParams().height = 80;

        viewHolder.itemView.requestLayout();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
