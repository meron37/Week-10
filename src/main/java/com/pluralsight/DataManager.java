package com.pluralsight;

import java.sql.*;
import java.util.ArrayList;

public class DataManager {

    //// search for actors by last name (you can type “DAVIS”, “CAGE”, etc.)
    public ArrayList<Actor> getActorsByLastName(String lastName) {
        ArrayList<Actor> actors = new ArrayList<>();

        String sql = "SELECT actor_id, first_name, last_name " +
                "FROM actor " +
                "WHERE last_name = ?";

        try (Connection connection = DataSourceFactory.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, lastName);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("actor_id");
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    actors.add(new Actor(id, first, last));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error getting actors: " + e.getMessage());
        }

        return actors;
    }

    // get all films for a given actor id
    public ArrayList<Film> getFilmsByActorId(int actorId) {
        ArrayList<Film> films = new ArrayList<>();

        String sql =
                "SELECT f.film_id, f.title, f.description, f.release_year, f.length " +
                        "FROM film f " +
                        "JOIN film_actor fa ON f.film_id = fa.film_id " +
                        "WHERE fa.actor_id = ?";

        try (Connection connection = DataSourceFactory.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, actorId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int filmId = rs.getInt("film_id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    int year = rs.getInt("release_year");
                    int length = rs.getInt("length");

                    films.add(new Film(filmId, title, description, year, length));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error getting films: " + e.getMessage());
        }

        return films;
    }
}
