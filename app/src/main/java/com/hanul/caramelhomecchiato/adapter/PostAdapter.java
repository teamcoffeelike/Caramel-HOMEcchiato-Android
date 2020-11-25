package com.hanul.caramelhomecchiato.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.hanul.caramelhomecchiato.R;
import com.hanul.caramelhomecchiato.data.Post;
import com.hanul.caramelhomecchiato.data.Reaction;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

	private final List<Post> posts = new ArrayList<>();
	private final Context ctx;

	public PostAdapter(Context ctx){
		this.ctx = ctx;
	}

	public List<Post> posts(){
		return posts;
	}

	@NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_post, parent, false));
	}
	@Override public void onBindViewHolder(@NonNull ViewHolder holder, int position){
		holder.setItem(position);
	}
	@Override public int getItemCount(){
		return posts.size();
	}


	public final class ViewHolder extends RecyclerView.ViewHolder{
		private final ImageView imageViewPostUserProfile;
		private final TextView textViewPostUser;
		private final ViewPager images;
		private final TextView textViewPost;

		private final ImageView imageViewReactionUserProfile;
		private final TextView textViewReactionUser;
		private final TextView textViewReaction;

		public ViewHolder(@NonNull View itemView){
			super(itemView);
			imageViewPostUserProfile = itemView.findViewById(R.id.imageViewPostUserProfile);
			textViewPostUser = itemView.findViewById(R.id.textViewPostUser);
			images = itemView.findViewById(R.id.images);
			textViewPost = itemView.findViewById(R.id.textViewPost);
			imageViewReactionUserProfile = itemView.findViewById(R.id.imageViewReactionUserProfile);
			textViewReactionUser = itemView.findViewById(R.id.textViewReactionUser);
			textViewReaction = itemView.findViewById(R.id.textViewReaction);

			itemView.findViewById(R.id.buttonLike).setOnClickListener(v -> {
				Toast.makeText(ctx, ";)", Toast.LENGTH_SHORT).show();
			});
		}

		private void setItem(int position){ // TODO 프로필 이미지와 첨부 사진
			Post post = posts.get(position);
			textViewPostUser.setText(post.getUser().getName());
			textViewPost.setText(post.getText());

			Reaction r = post.getFirstReaction();
			if(r!=null){
				textViewReactionUser.setText(r.getUser().getName());
				textViewReaction.setText(r.getText());
			}// TODO 댓글이 없을 시에는 보이지 않아야 함
		}
	}
}