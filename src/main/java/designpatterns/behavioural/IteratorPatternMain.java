package designpatterns.behavioural;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Concrete Iterator
class Post {
    private String content;

    public Post(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Aggregate
class SocialMediaFeed implements Iterable<Post> {
    private List<Post> posts;

    public SocialMediaFeed() {
        posts = new ArrayList<>();
    }

    public void addPost(Post post) {
        posts.add(post);
    }
    @Override
    public Iterator<Post> iterator() {
        return posts.iterator();
    }
}


/**
 * When to Use:
 *
 * 1. When dealing with problems explicitly related to iteration.
 *
 * 2. For designing flexible looping constructs and accessing elements
 *    from a complex collection without knowing the underlying representation.
 *
 * 3. To implement a generic iterator that traverses any collection independently of its type.
 *
 * Example: Social Media Feed
 * Imagine a social media application that allows you to iterate through user posts.
 */
public class IteratorPatternMain {
    public static void main(String[] args) {
        SocialMediaFeed feed = new SocialMediaFeed();
        feed.addPost(new Post("Hello, world!"));
        feed.addPost(new Post("My second post"));
        feed.addPost(new Post("Check out this cool photo!"));

        for (Post post : feed) {
            System.out.println(post.getContent());
        }
    }
}
