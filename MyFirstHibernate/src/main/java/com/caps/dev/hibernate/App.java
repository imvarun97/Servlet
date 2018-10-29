package com.caps.dev.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.caps.dev.beans.Movie;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		System.out.println("Enter Movie Details");
		System.out.println("-------------------");
		Movie movie = new Movie();
		Scanner in = new Scanner(System.in);

		System.out.println("Enter movie id: ");
		movie.setMovieId(Integer.parseInt(in.nextLine()));

		System.out.println("Enter movie name: ");
		movie.setMovieName(in.nextLine());

		System.out.println("Enter movie ratings: ");
		movie.setRatings(Double.parseDouble(in.nextLine()));

		System.out.println("Enter movie summary: ");
		movie.setSummmary(in.nextLine());
		save(movie);
		System.out.println("Movie Saved");
	}
	
	public static void save(Movie movie) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(movie);
		tx.commit();
	}
}
