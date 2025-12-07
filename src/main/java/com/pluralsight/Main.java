package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = new DataManager();

        ArrayList<Actor> actors;

        // KEEP ASKING FOR LAST NAME UNTIL RESULTS ARE FOUND
        while (true) {
            System.out.print("Enter an actor's last name: ");
            String lastName = scanner.nextLine();

            actors = dataManager.getActorsByLastName(lastName);

            if (actors.isEmpty()) {
                System.out.println("No actors found with that last name. Try again.\n");
            } else {
                break;  // results found exit loop
            }
        }

        // Display matching actors
        System.out.println("\nActors found:");
        for (Actor actor : actors) {
            System.out.println(actor); // prints "id - first last"
        }

        // Ask user to pick an actor ID
        System.out.print("\nEnter an actor id to see their movies: ");
        int actorId = Integer.parseInt(scanner.nextLine());

        // Get films for that actor
        ArrayList<Film> films = dataManager.getFilmsByActorId(actorId);

        if (films.isEmpty()) {
            System.out.println("No films found for that actor.");
        } else {
            System.out.println("\nFilms for actor id " + actorId + ":");
            for (Film film : films) {
                System.out.println(film); // uses Film.toString()
            }
        }
    }
}
