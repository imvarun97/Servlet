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
import com.caps.model.JPAUtils;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		//save();
		//getMovieById();
		//remove();
		//getMovieById();
		Update();
	}
	
	public static void save() {
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
		System.out.println("Movie Saved");
		EntityManagerFactory emf = JPAUtils.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(movie);
		tx.commit();
		em.close();
			
		in.close();
	}
	
	public static void getMovieById() {
		System.out.println("Enter a movie id: ");
		Scanner sc = new Scanner(System.in);
		int id = Integer.parseInt(sc.nextLine());
		EntityManagerFactory emf = JPAUtils.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Movie m = em.getReference(Movie.class, id);
		tx.commit();
		em.close();
		emf.close();
		sc.close();
		System.out.println(m);
	}
	public static void remove()
	{
		System.out.println("Enter a movie id to del: ");
		Scanner sc = new Scanner(System.in);
		int id = Integer.parseInt(sc.nextLine());
		EntityManagerFactory emf = JPAUtils.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Movie m = em.getReference(Movie.class, id);
		em.remove(m);
		tx.commit();
		em.close();
		emf.close();
		sc.close();
		System.out.println(m);
	}
	public static void Update()
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a movie id to update: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Enter ratings: ");
		Double ratings = Double.parseDouble(sc.nextLine());
		EntityManagerFactory emf = JPAUtils.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Movie m = em.find(Movie.class, id);
		m.setRatings(ratings);
		
		tx.commit();
		em.close();
		emf.close();
		sc.close();
		System.out.println(m);
	}
}