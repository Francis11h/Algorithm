Favorite Genres "ˈZHänrə" 类型

Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.

Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. 
"The song can only belong to only one genre".


The task is to return a map Map<String, List<String>>, 
where the key is a user name and 
      the value is a list of the user‘s favorite genre(s). 

Favorite genre is the most listened to genre. 
A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.

Example 1:

Input:
userSongs = {  
   "David": ["song1", "song2", "song3", "song4", "song8"],
   "Emma":  ["song5", "song6", "song7"]
},
songGenres = {  
   "Rock":    ["song1", "song3"],
   "Dubstep": ["song7"],
   "Techno":  ["song2", "song4"],
   "Pop":     ["song5", "song6"],
   "Jazz":    ["song8", "song9"]
}

Output: {  
   "David": ["Rock", "Techno"],
   "Emma":  ["Pop"]
}

Explanation:
David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
Emma has 2 Pop and 1 Dubstep song. Pop is Emma‘s favorite genre.




Example 2:

Input:
userSongs = {  
   "David": ["song1", "song2"],
   "Emma":  ["song3", "song4"]
},
songGenres = {}

Output: {  
   "David": [],
   "Emma":  []
}



1. Create song ---> genre Map
2. For each user create the genre ---> count Map
3. For each user find the genre(s) with the max count






import java.util.*;

public class FavoriteGenre {

    public static Map<String, List<String>> favoritegenre(Map<String, List<String>> userSongs, Map<String, List<String>> songGenres) {
        Map<String, List<String>> res = new HashMap<>();
        //if (songGenres == null || songGenres.isEmpty()) return res;
        if (userSongs == null || userSongs.isEmpty()) return res;

        // build song ---> genre Map
        Map<String, String> songToGenra = new HashMap<>();
        for (String genre : songGenres.keySet()) {
            List<String> songs = songGenres.get(genre);
            for (String song : songs) {
                songToGenra.put(song, genre);
            }
        }
        // for each user create gener ---> count Map

        for (String user : userSongs.keySet()) {
            Map<String, Integer> countMap = new HashMap<>();
            int max = Integer.MIN_VALUE;
            res.put(user, new LinkedList<>());

            List<String> songs = userSongs.get(user);
            for (String song : songs) {
                //这行 必须要加 否则 结果会是 {David=[null], Emma=[null]} 这就是ibug
                if (!songToGenra.containsKey(song)) continue;
                String genre = songToGenra.get(song);
                int count = countMap.getOrDefault(genre, 0) + 1;
                countMap.put(genre, count);
                max = Math.max(count, max);
            }

            for (String key : countMap.keySet()) {
                if (countMap.get(key) == max) {
                    res.get(user).add(key);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<String, List<String>> userSongs = new HashMap<>();
        Map<String, List<String>> songGenres = new HashMap<>();

//        userSongs.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
//        userSongs.put("Emma", Arrays.asList("song5", "song6", "song7"));
//
//        songGenres.put("Rock", Arrays.asList("song1", "song3"));
//        songGenres.put("Dubstep",  Arrays.asList("song7"));
//        songGenres.put("Techno",  Arrays.asList("song2", "song4"));
//        songGenres.put("Pop",  Arrays.asList("song5", "song6"));
//        songGenres.put("Jazz", Arrays.asList("song8", "song9") );

        userSongs.put("David", Arrays.asList("song1", "song2"));
        userSongs.put("Emma", Arrays.asList("song3", "song4"));

        System.out.println(favoritegenre(userSongs, songGenres));

    }
}

// 1. FavoriteGenre
// dereference 前我都check了一下是不是null
// 这里头 map.get() 出来的值可能会是null，比如 一个genre 类别里头可能没有 book，或者 某一个book 不属于任何genre
// 额外的package 需要自己import，楼主用了ArrayList，一开始看到报错的时候愣住了


// book genre 有同学会有runtime error 我check了两个条件 1. genre里有book 而不是empty list或者null 2. book有genre 然后就全过了

// input里有个genre to song的map，genre里没有song的话对应value会是null或者empty list？我建song to genre的时候check了一下