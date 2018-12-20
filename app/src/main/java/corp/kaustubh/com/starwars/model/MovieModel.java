package corp.kaustubh.com.starwars.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MovieModel implements Serializable,Parcelable {

    private int id;
    @Expose
    @SerializedName("url")
    private String url;
    @Expose
    @SerializedName("edited")
    private String edited;
    @Expose
    @SerializedName("created")
    private String created;
    @Expose
    @SerializedName("species")
    private List<String> species;
    @Expose
    @SerializedName("vehicles")
    private List<String> vehicles;
    @Expose
    @SerializedName("starships")
    private List<String> starships;
    @Expose
    @SerializedName("planets")
    private List<String> planets;
    @Expose
    @SerializedName("characters")
    private List<String> characters;
    @Expose
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    @SerializedName("producer")
    private String producer;
    @Expose
    @SerializedName("director")
    private String director;
    @Expose
    @SerializedName("opening_crawl")
    private String openingCrawl;
    @Expose
    @SerializedName("episode_id")
    private int episodeId;
    @Expose
    @SerializedName("title")
    private String title;

    public MovieModel(String url, String edited, String created, List<String> species, List<String> vehicles, List<String> starships, List<String> planets, List<String> characters, String releaseDate, String producer, String director, String openingCrawl, int episodeId, String title) {
        this.url = url;
        this.edited = edited;
        this.created = created;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
        this.planets = planets;
        this.characters = characters;
        this.releaseDate = releaseDate;
        this.producer = producer;
        this.director = director;
        this.openingCrawl = openingCrawl;
        this.episodeId = episodeId;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeString(this.edited);
        dest.writeString(this.created);
        dest.writeStringList(this.species);
        dest.writeStringList(this.vehicles);
        dest.writeStringList(this.starships);
        dest.writeStringList(this.planets);
        dest.writeStringList(this.characters);
        dest.writeString(this.releaseDate);
        dest.writeString(this.producer);
        dest.writeString(this.director);
        dest.writeString(this.openingCrawl);
        dest.writeInt(this.episodeId);
        dest.writeString(this.title);
    }

    protected MovieModel(Parcel in) {
        this.id = in.readInt();
        this.url = in.readString();
        this.edited = in.readString();
        this.created = in.readString();
        this.species = in.createStringArrayList();
        this.vehicles = in.createStringArrayList();
        this.starships = in.createStringArrayList();
        this.planets = in.createStringArrayList();
        this.characters = in.createStringArrayList();
        this.releaseDate = in.readString();
        this.producer = in.readString();
        this.director = in.readString();
        this.openingCrawl = in.readString();
        this.episodeId = in.readInt();
        this.title = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
