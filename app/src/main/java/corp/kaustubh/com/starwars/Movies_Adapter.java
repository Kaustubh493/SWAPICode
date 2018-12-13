package corp.kaustubh.com.starwars;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Movies_Adapter extends RecyclerView.Adapter<Movies_Adapter.ViewHolder> {
    private List<Movies_model> movies_dataset;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_movies_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public Movies_Adapter(List<Movies_model> movies_models, Context mContext) {
        super();
        this.movies_dataset = movies_models;
        this.context = mContext;
        Log.e( "Movies_Adapter: ",movies_models.toString() );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
holder.txt_Producer.setText(movies_dataset.get(position).getProducer());
holder.txt_Title.setText(movies_dataset.get(position).getTitle());
holder.txt_Released_Date.setText(movies_dataset.get(position).getReleaseDate());
holder.txt_movie_nav.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(context, "You Have Clicked" + position, Toast.LENGTH_SHORT).show();
    }
});
    }

    @Override
    public int getItemCount() {
        return movies_dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txt_Title, txt_Producer, txt_Released_Date, txt_movie_nav;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txt_Title = v.findViewById(R.id.txt_Title);
            txt_Producer = v.findViewById(R.id.txt_Producer);
            txt_Released_Date = v.findViewById(R.id.txt_Release_Date);
            txt_movie_nav = v.findViewById(R.id.txt_movie_nav);
        }
    }
}
