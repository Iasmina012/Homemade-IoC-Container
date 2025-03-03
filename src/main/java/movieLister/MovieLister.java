package movieLister;

public class MovieLister {

    private final MovieFinder movieFinder;

    //Constructorul ar trebui sa primeasca un argument de tip movieLister.MovieFinder
    public MovieLister(MovieFinder movieFinder) { this.movieFinder = movieFinder; }

    public void listMovies() { System.out.println("Movies found: " + movieFinder.findMovies()); }

}