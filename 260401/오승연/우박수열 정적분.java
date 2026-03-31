import java.io.*;
import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> list=new ArrayList<>();
        list.add(k);
        while(k>1){
            if(k%2==0){
                k/=2;
                list.add(k);
            }else{
                k*=3;
                k+=1;
                list.add(k);
            }
        }
        // System.out.println(list.toString());
        int end=list.size()-1;
        
        double[] answer = new double[ranges.length];
        for(int i=0;i<ranges.length;i++){
            int s=ranges[i][0];
            int e=end+ranges[i][1];

            if(e-s<0){
                answer[i]=-1.0;
            }else{
                double sum=0.0;
                for(int j=s;j<e;j++){
                    sum += (list.get(j) + list.get(j+1)) / 2.0;
                }
                answer[i]=sum;
            }
        }
        
        return answer;
    }
}
