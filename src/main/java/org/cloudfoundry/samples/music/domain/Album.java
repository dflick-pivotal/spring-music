package org.cloudfoundry.samples.music.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxWriter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Entity
@Region("Album")
public class Album implements PdxSerializable {
	@Id
	private String id;

	private String title;
	private String artist;
	private String releaseYear;
	private String genre;
	private int trackCount;
	private String albumId;

	@PersistenceConstructor
	public Album() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public Album(String title, String artist, String releaseYear, String genre) {
		this.title = title;
		this.artist = artist;
		this.releaseYear = releaseYear;
		this.genre = genre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	@Override
	public void toData(PdxWriter writer) {
		writer.writeString("id", id);
		writer.writeString("title", title);
		writer.writeString("artist", artist);
		writer.writeString("releaseYear", releaseYear);
		writer.writeInt("trackCount", trackCount);
		writer.writeString("albumId", albumId);
	}

	@Override
	public void fromData(PdxReader reader) {
		id = reader.readString("id");
		title = reader.readString("title");
		artist = reader.readString("artist");
		releaseYear = reader.readString("releaseYear");
		genre = reader.readString("genre");
		trackCount = reader.readInt("trackCount");
		albumId = reader.readString("albumId");
	}

}
