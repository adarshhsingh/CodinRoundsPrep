package leetcode.socialmedia;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Content {
    int ID;
    AtomicInteger popularity;
    Content(int ID) {
        this.ID = ID;
        popularity = new AtomicInteger(0);
    }
    void updatePopularity(boolean increment) {
        if(increment) {
            popularity.incrementAndGet();
        } else if (popularity.get()>0) {
            popularity.decrementAndGet();
        }
    }
}

class SocialMedia {
    PriorityQueue<Content> top3Content;
    HashMap<String,Boolean> popularityActionMap;
    HashMap<Integer, Content> contentList;
    SocialMedia() {
     contentList = new HashMap<>();
     popularityActionMap = new HashMap<>();
     popularityActionMap.put("commentAdd",true);
     popularityActionMap.put("commentDelete",false);
     popularityActionMap.put("botActionDetection",false);
     popularityActionMap.put("like",true);
     popularityActionMap.put("dislike",false);

     top3Content = new PriorityQueue<>(1, new Comparator<Content>() {
         @Override
         public int compare(Content o1, Content o2) {
             return o1.popularity.get() - o2.popularity.get();
         }
     });
    }

    void addContent(Integer contentID) {
        contentList.putIfAbsent(contentID, new Content(contentID));
    }
    void checkActionAndUpdatePopularity(Integer contentID, String action) {
        Content content = contentList.get(contentID);
        Boolean actionMapping = popularityActionMap.get(action);
        if (content != null && actionMapping!=null) {
            synchronized(content) {
                content.updatePopularity(actionMapping);
            }
        }
        if(content.popularity.get() > 0) {
            updateTop3Content(content);
        } else {
            top3Content.remove(content);
        }
    }

    String getTopN() {
        List<Content> top = new ArrayList<>();
        String s = "";
        while (!top3Content.isEmpty()) {
            s += top3Content.peek().ID + " - " + top3Content.peek().popularity+ " , ";
            top.add(top3Content.poll());
        }
        top3Content.addAll(top);
        return s;
    }

    void updateTop3Content(Content content) {
        synchronized (top3Content) {
            boolean wasExistingInTop3 = top3Content.remove(content);
            if (wasExistingInTop3) {
                top3Content.add(content);
                return;
            }
            if (!wasExistingInTop3 && top3Content.size() < 3) {
                top3Content.add(content);
                return;
            }
            // check if size == 3
            if (!wasExistingInTop3 && top3Content.size() == 3 && content.popularity.get() > top3Content.peek().popularity.get()) {
                top3Content.poll();
                top3Content.add(content);
            }


        }
    }
}

public class SociaMediaEventStream {

    SocialMedia INSTA = new SocialMedia();

    public static void main(String[] args) {
        SocialMedia INSTA = new SocialMedia();

        INSTA.addContent(1);
        INSTA.addContent(2);
        INSTA.addContent(3);
        INSTA.addContent(4);
        INSTA.addContent(5);
        INSTA.addContent(6);

        System.out.println("** top 3**");
        INSTA.top3Content.forEach(c -> {
            System.out.print(c.ID + " "+ c.popularity+ " , ");
        });
        System.out.println("");
        INSTA.checkActionAndUpdatePopularity(1, "like");
        System.out.println("** top 3**");
        System.out.print(INSTA.getTopN());

        System.out.println();
        INSTA.checkActionAndUpdatePopularity(1, "like");
        INSTA.checkActionAndUpdatePopularity(1, "like");
        INSTA.checkActionAndUpdatePopularity(1, "like");
        INSTA.checkActionAndUpdatePopularity(2, "like");
        INSTA.checkActionAndUpdatePopularity(3, "like");
        INSTA.checkActionAndUpdatePopularity(4, "like");
        System.out.println("** top 3**");
        System.out.print(INSTA.getTopN());
        System.out.println("");
        INSTA.checkActionAndUpdatePopularity(3, "like");
        INSTA.checkActionAndUpdatePopularity(4, "like");
        INSTA.checkActionAndUpdatePopularity(4, "like");
        System.out.println("** top 3**");
        System.out.print(INSTA.getTopN());
    }
}
