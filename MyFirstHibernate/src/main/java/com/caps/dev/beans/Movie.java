package com.caps.dev.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies_info")
public class Movie {
	@Id @Column(name="movie_id")
	private int movieId;
	
	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="movie_summary")
	private String summmary;
	
	@Column(name="movie_ratings")
	private Double ratings;
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getSummmary() {
		return summmary;
	}
	public void setSummmary(String summmary) {
		this.summmary = summmary;
	}
	public Double getRatings() {
		return ratings;
	}
	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}
	
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", summmary=" + summmary + ", ratings="
				+ ratings + "]";
	}
}