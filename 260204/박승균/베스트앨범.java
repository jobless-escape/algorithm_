import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, Integer> genreMap = new HashMap<>();
        Map<String, List<int[]>> playMap = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            String genre = genres[i];
            int play = plays[i];

            genreMap.put(genre, genreMap.getOrDefault(genre, 0) + play);

            playMap.putIfAbsent(genre, new ArrayList<>());
            playMap.get(genre).add(new int[]{i, play});
        }
        
        List<Integer> result = new ArrayList<>();
        
        List<String> sortedList = new ArrayList<>(genreMap.keySet());
        sortedList.sort((a, b) -> genreMap.get(b) - genreMap.get(a));
        
        for (String genre : sortedList) {
            List<int[]> playCnt = playMap.get(genre); // 노래 번호, 재생수

            playCnt.sort((a, b) -> {
                if (b[1] != a[1]) return b[1] - a[1];
                else return a[0] - b[0];
            });

            for (int i = 0; i < playCnt.size(); i++) {
                if(i == 2)
                    break;
                result.add(playCnt.get(i)[0]);
            }
        }
        
        answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}