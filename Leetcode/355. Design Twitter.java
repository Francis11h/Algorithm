355. Design Twitter

Design a simplified version of Twitter 
where users can post tweets, follow/unfollow another user 
and is able to see the 10 most recent tweets in the user‘s news feed. 

Your design should support the following methods:

postTweet(userId, tweetId): 		Compose a new tweet.
getNewsFeed(userId): 				Retrieve the 10 most recent tweet ids in the user’s news feed. 
									Each item in the news feed must be posted by users who the user followed or by the user herself. 
									Tweets must be ordered from most recent to least recent.

follow(followerId, followeeId): 	Follower follows a followee.
unfollow(followerId, followeeId): 	Follower unfollows a followee.





Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);







-----------------------------------------------------------------------------------------------------------

OOD

1. Data structure need in the Tweet system:

	1.1 a data structure that saves the Following relationship
			===> 	Map(follower, Set(followees))
	1.2 a data structure that saves the tweets posted
			===> 	Map(userId, List(Tweet))

2. Based on the requirment of method3: 
	we should get our followee‘s tweets and get the most recent 10 tweet.
	So there should be a Timestamp inside the tweet. 定义一个类的静态变量作为计数器来实现

	2.1 a class Tweet containing timestamp
			===> 	Tweet(tweetId, timePosted);

			
tips 
one should get the tweets of itself, which means the set of followee must contain itself
since the followee must contains itself, it cannot unfollow itself (unfollow add this constraint)
the followees must be identical





public class Twitter{

	public static class Tweet{
		int tweetId;
		int timePosted;
		public Tweet(int tweetId, int timePosted) {
			this.tweetId = tweetId;
			this.timePosted = timePosted;
		}
	}

	static int timeStamp;
	Map<Integer, Set<Integer>> followees;
	Map<Integer, List<Tweet>> tweets;
    static int feedMaxNum;

	public Twitter() {
		timeStamp = 0;
		followees = new HashMap<>();
		tweets = new HashMap<>();
        feedMaxNum = 10;
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if (!tweets.containsKey(userId)) {
			//build one's tweet list
			tweets.put(userId, new LinkedList<>());
			//follow himself
			follow(userId, userId);
		}
		//add new tweet on the first place
		tweets.get(userId).add(0, new Tweet(tweetId, timeStamp++));
	} 

	/** Retrieve the 10 most recent tweet ids in the user's news feed. 
		Each item in the news feed must be posted by users who the user followed or by the user himself. 
		Tweets must be ordered from most recent to least recent. 
	*/
	public List<Integer> getNewsFeed(int userId) {
		//minHeap the top is the old tweet, which maybe remove from the list
		PriorityQueue<Tweet> minHeap = new PriorityQueue<>(new Comparator<Tweet>(){
			@Override
			public int compare(Tweet t1, Tweet t2) {
				return t1.timePosted - t2.timePosted;
			}
		});
		// get my followee Set
		Set<Integer> myFollowees = followees.get(userId);
		if (myFollowees != null) {
			for (Integer followeeId : myFollowees) {
				//get the tweets of them
				List<Tweet> followeeTweets = tweets.get(followeeId);
				if (followeeTweets != null) {
					for (Tweet tweet : followeeTweets) {
						if (minHeap.size() < feedMaxNum) {
							minHeap.offer(tweet);
						} else {
							//if it is older than the oldest one in the heap just discard
							if (tweet.timePosted <= minHeap.peek().timePosted) break;
							minHeap.offer(tweet);
							minHeap.poll();
						}
					}
				}
			}
		}
		List<Integer> myFeed = new LinkedList<>();
		while (!minHeap.isEmpty()) {
			myFeed.add(0, minHeap.poll().tweetId);
		}
		return myFeed;
	}



	/** follower follows a followee */	
	public void follow(int followerId, int followeeId) {
		//
		if (!followees.containsKey(followerId)) {
			followees.put(followerId, new HashSet<>());
		}
		followees.get(followerId).add(followeeId);
	}
	/** follower unfollows a followee but one can't unfollow himself*/
	public void unfollow(int followerId, int followeeId) {
		if (!followees.containsKey(followerId) || followerId == followeeId) return;
		followees.get(followerId).remove(followeeId);
	}
}































