package com.example.solvychi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class LevelsAdapter extends ArrayAdapter<level> {

    Context adapterContext;
    int adapterResource;
    level[] adapterLevels;
    String UserEmail;
    int currentLevel;
    public LevelsAdapter(@NonNull Context context, int resource, @NonNull level[] levels,String email,int level) {
        super(context, resource, levels);
        adapterContext = context;
        adapterResource = resource;
        adapterLevels = levels;
        UserEmail = email;
        currentLevel = level;
    }

    @Nullable
    @Override
    public level getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        level current = adapterLevels[position];
        String selectedLevel =current.selectedLevelName;
        LayoutInflater inflaterRow = LayoutInflater.from(adapterContext);
        row = inflaterRow.inflate(adapterResource,parent,false);
        TextView levelName = (TextView) row.findViewById(R.id.level_name);
        TextView levelDesc = (TextView) row.findViewById(R.id.level_desc);
        AppCompatButton btn = (AppCompatButton) row.findViewById(R.id.btnlevel);
        LinearLayout layout = (LinearLayout) row.findViewById(R.id.level);
        int bgId = adapterContext.getResources().getIdentifier(current.bg,"drawable",adapterContext.getPackageName());
        //==================adapt button to level =========================
        if(position<currentLevel)
        {

            btn.setText("REPLAY");
            btn.setEnabled(true);
            btn.setBackgroundResource(R.drawable.button);

        }

        else if(position>currentLevel)
        {


            btn.setText("LOCKED 🔒");

            btn.setBackgroundResource(R.drawable.button_lock);
            btn.setEnabled(false);

        }
        else if(position==currentLevel)
        {
            btn.setText("START");
            btn.setEnabled(true);
            btn.setBackgroundResource(R.drawable.button);
        }



        //=========================set our template values

        levelName.setText(current.name);
        levelDesc.setText(current.desc);
        layout.setBackgroundResource(bgId);
        // add event listener to btn
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle data = new Bundle();

                data.putString("selectedLevel",selectedLevel);
                data.putString("email", UserEmail);
                //============level one and two
                if(selectedLevel.equals("level1")||selectedLevel.equals("level2"))
                {
                    Intent intent = new Intent(adapterContext, questionActivity.class);
                    intent.putExtras(data);
                    adapterContext.startActivity(intent);
                }
                //============level one and two
                else if(selectedLevel.equals("level3"))
                {
                    Intent intent = new Intent(adapterContext, GameActivity.class);
                    intent.putExtras(data);
                    adapterContext.startActivity(intent);
                }
                else if(selectedLevel.equals("level4"))
                {
                    Intent intent = new Intent(adapterContext, PuzzleActivity.class);
                    intent.putExtras(data);
                    adapterContext.startActivity(intent);
                }
//                Toast.makeText(adapterContext, UserEmail, Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }
    public View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition =firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition ) {
            return listView.getAdapter().getView(position, listView.getChildAt(position), listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}

