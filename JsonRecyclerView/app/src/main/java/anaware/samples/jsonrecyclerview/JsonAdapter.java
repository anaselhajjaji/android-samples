package anaware.samples.jsonrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JsonAdapter extends android.support.v7.widget.RecyclerView.Adapter {
    private List<Song> songs;

    public JsonAdapter(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new JsonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Song song = songs.get(position);
        ((JsonViewHolder) holder).bindViewHolder(song);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class JsonViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {

        @Bind(R.id.artistTv)
        TextView artistTv;

        @Bind(R.id.timesTv)
        TextView timeTv;

        @Bind(R.id.trackIdTv)
        TextView trackIdTv;

        @Bind(R.id.titleTv)
        TextView titleTv;

        @Bind(R.id.songImage)
        ImageView songImage;

        public JsonViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindViewHolder(Song song) {
            artistTv.setText(song.getArtist());
            timeTv.setText(song.getSongDate().toString());
            trackIdTv.setText(song.getTrackId());
            titleTv.setText(song.getTitle());

            // Download image
            Picasso.with(titleTv.getContext())
                    .load(song.getTrackImage())
                    .placeholder(android.R.drawable.ic_menu_rotate)
                    .error(android.R.drawable.ic_menu_camera)
                    .into(songImage);
        }
    }
}




