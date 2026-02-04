import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genrePlayCount = new HashMap<>();
        HashMap<String, ArrayList<Song>> genreSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            genrePlayCount.put(genre, genrePlayCount.getOrDefault(genre, 0) + play);

            if (!genreSongs.containsKey(genre)) {
                genreSongs.put(genre, new ArrayList<>());
            }
            genreSongs.get(genre).add(new Song(i, play));
        }

        List<String> genreOrder = new ArrayList<>(genrePlayCount.keySet());
        genreOrder.sort((a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));

        for (String genre : genreSongs.keySet()) {
            genreSongs.get(genre).sort((a, b) -> {
                if (a.play != b.play) {
                    return b.play - a.play;
                }
                return a.id - b.id;
            });
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (String genre : genreOrder) {
            ArrayList<Song> songs = genreSongs.get(genre);

            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                answer.add(songs.get(i).id);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    class Song {
        int id;
        int play;

        Song(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }
}