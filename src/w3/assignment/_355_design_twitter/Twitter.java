package w3.assignment._355_design_twitter;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Twitter {
    //userId -> tweetIds
    Map<Integer, Queue<TweetItem>> userTweets = new HashMap<>();
    //follower -> followee
    Map<Integer, Set<Integer>> followerFollowee = new HashMap<>();

    int LIMIT = 10;

    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        Queue<TweetItem> tweets = userTweets.getOrDefault(userId, new LinkedList<TweetItem>());
        tweets.add(new TweetItem(userId, tweetId));
        userTweets.putIfAbsent(userId, tweets);

        if (tweets.size() > LIMIT) tweets.remove();
    }

    public List<Integer> getNewsFeed(int userId) {
        List<TweetItem> ans = new ArrayList<>();
        Queue<TweetItem> ownTweets = userTweets.getOrDefault(userId, new LinkedList<TweetItem>());

        ans.addAll(ownTweets);

        for (Integer followeeId: followerFollowee.getOrDefault(userId, (Set<Integer>)Collections.EMPTY_SET)) {
            ans.addAll(userTweets.getOrDefault(followeeId, new LinkedList<TweetItem>()));
        }

        ToIntFunction<TweetItem> tweetItemIntConverter = (TweetItem i) -> i.seq;
        return ans.stream().sorted(Comparator.comparingInt(tweetItemIntConverter).reversed()).limit(LIMIT).mapToInt((TweetItem o) -> o.tweetId).boxed().collect(Collectors.toList());
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> followeeIds = followerFollowee.getOrDefault(followerId, new HashSet<Integer>());
        followeeIds.add(followeeId);
        followerFollowee.putIfAbsent(followerId, followeeIds);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followeeIds = followerFollowee.getOrDefault(followerId, new HashSet<Integer>());

        if (followeeIds.isEmpty()) return;

        followeeIds.remove(followeeId);
    }
}
class TweetItem {
    int tweetId;
    int userId;
    int seq;
    private static int timeSeq = Integer.MIN_VALUE;
    public TweetItem(int userId, int tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
        seq = timeSeq++;
    }
}