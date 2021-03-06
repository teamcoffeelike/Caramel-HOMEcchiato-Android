package com.hanul.caramelhomecchiato.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public final class UserProfile implements Parcelable{
	public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>(){
		@Override public UserProfile createFromParcel(Parcel in){
			return new UserProfile(in);
		}
		@Override public UserProfile[] newArray(int size){
			return new UserProfile[size];
		}
	};

	private User user;
	@Nullable private String motd;

	public UserProfile(User user, @Nullable String motd){
		this.user = user;
		this.motd = motd;
	}
	protected UserProfile(Parcel in){
		user = in.readParcelable(User.class.getClassLoader());
		motd = in.readString();
	}

	@Override public int describeContents(){
		return 0;
	}
	@Override public void writeToParcel(Parcel dest, int flags){
		dest.writeParcelable(user, flags);
		dest.writeString(motd);
	}

	public User getUser(){
		return user;
	}
	public void setUser(User user){
		this.user = user;
	}
	@Nullable public String getMotd(){
		return motd;
	}
	public void setMotd(@Nullable String motd){
		this.motd = motd;
	}

	@Override public String toString(){
		return "UserProfile{"+
				"user="+user+
				", motd='"+motd+'\''+
				'}';
	}

	public enum Json implements JsonDeserializer<UserProfile>{
		INSTANCE;

		@Override public UserProfile deserialize(JsonElement json,
		                                         Type typeOfT,
		                                         JsonDeserializationContext context) throws JsonParseException{
			JsonElement motd = json.getAsJsonObject().get("motd");
			return new UserProfile(
					context.deserialize(json, User.class),
					motd==null ? null : motd.getAsString());
		}
	}
}
