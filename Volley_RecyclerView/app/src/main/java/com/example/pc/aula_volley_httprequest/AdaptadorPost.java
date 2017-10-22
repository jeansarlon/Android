package com.example.pc.aula_volley_httprequest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by PC on 03/10/2016.
 */

public class AdaptadorPost extends RecyclerView.Adapter<AdaptadorPost.PostViewHolder> {

    private List<Post> posts;
    private Context context;

    public AdaptadorPost(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //preciamos criar uma view para que o nosso código funcione
        View view = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        PostViewHolder holder = new PostViewHolder(view); //passa a view que foi instanciada
        return holder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        //pega o post na posição da lista
        Post post = posts.get(position);
        //seta os valores dos elementos de interface
        holder.tvTitulo.setText(post.getTitulo());
        holder.tvSubtitulo.setText(post.getSubtitulo());
        holder.tvUsuario.setText(post.getUsuario());
        holder.tvTexto.setText(post.getTexto());
        holder.tvData.setText(post.getData());

        new DownloadImageTask(holder.ivFoto).execute("http://192.168.58.1:80/PDM2/"+post.getFoto().toString());

        holder.tvUsuario.setTypeface(holder.tfL);
        holder.tvData.setTypeface(holder.tfL);
        holder.tvTitulo.setTypeface(holder.tfR);
        holder.tvSubtitulo.setTypeface(holder.tfL);
        holder.tvTexto.setTypeface(holder.tfR);

    }

    @Override
    public int getItemCount() {
        return posts.size();
        //return 0;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        final ImageView ivFoto;
        final TextView tvTitulo;
        final TextView tvSubtitulo;
        final TextView tvUsuario;
        final TextView tvTexto;
        final TextView tvData;
        final Typeface tfL;
        final Typeface tfR;

        public PostViewHolder(View itemView) {
            super(itemView);

            //instancia os elementos de interface
            ivFoto = (ImageView) itemView.findViewById(R.id.ivPost);
            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo = (TextView) itemView.findViewById(R.id.tvSubtitulo);
            tvUsuario = (TextView) itemView.findViewById(R.id.tvUsuario);
            tvTexto = (TextView) itemView.findViewById(R.id.tvTextoPost);
            tvData = (TextView) itemView.findViewById(R.id.tvData);
            tfL = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Lato-Light.ttf");
            tfR = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Lato-Regular.ttf");

        }
    }
}


class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}

