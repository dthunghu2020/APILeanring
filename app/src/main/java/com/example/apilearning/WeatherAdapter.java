package com.example.apilearning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apilearning.data.model.Weather;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Weather> mWeathers;

    public WeatherAdapter(Context context, List<Weather> weatherPosts) {
        inflater = LayoutInflater.from(context);
        this.mWeathers = weatherPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Weather weather = mWeathers.get(position);
        holder.txtWeather.setText("" + weather.getId() + " " + weather.getMain() + " " + weather.getDescription() + " " + weather.getIcon());
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtWeather;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWeather = itemView.findViewById(R.id.txtItemWeather);
        }
    }
}
