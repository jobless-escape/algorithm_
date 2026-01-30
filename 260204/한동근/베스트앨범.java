import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        int n = plays.length;
        
        HashMap<String, Integer> genreTotalPlay = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> genreInfo = new HashMap<>();
        
        for(int i = 0  ; i < n ; i++){
            String genre = genres[i];
            int play = plays[i];
            if(!genreInfo.containsKey(genre)){
                HashMap<Integer, Integer> musicPlay = new HashMap<>();
                musicPlay.put(i, play);
                genreInfo.put(genre, musicPlay);
            }
            else{
                genreInfo.get(genre).put(i, play);
            }
            
            genreTotalPlay.put(genre, genreTotalPlay.getOrDefault(genre, 0) + play);
        }
        
        ArrayList<String> keySet = new ArrayList<>(genreTotalPlay.keySet());
        Collections.sort(keySet, (o1, o2) -> genreTotalPlay.get(o2) - genreTotalPlay.get(o1));
        
        for(String key : keySet){
            HashMap<Integer, Integer> musicIndex = new HashMap<>(genreInfo.get(key));
            ArrayList<Integer> index = new ArrayList<>(musicIndex.keySet());
            Collections.sort(index, (o1, o2) -> musicIndex.get(o2) - musicIndex.get(o1));
            
            answer.add(index.get(0));
            if(index.size() > 1) answer.add(index.get(1));
        }
        
        int[] answerArr = new int[answer.size()];
        for(int i = 0 ; i < answer.size() ; i++) answerArr[i] = answer.get(i);
        
        return answerArr;
    }
}