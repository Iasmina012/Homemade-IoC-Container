package movieLister;

import java.util.Arrays;
import java.util.List;

public class MovieFinderImpl implements MovieFinder {

    private final DatabaseAccess databaseAccess;

    public MovieFinderImpl(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    @Override
    public List<String> findMovies() {

        databaseAccess.accessDatabase();
        System.out.println("Finding movies using database...");
        return Arrays.asList("Fast & Furious", "Tomb Raider", "Pirates of the Caribbean");

    }

}