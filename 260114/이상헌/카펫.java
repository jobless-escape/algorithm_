class Solution {
    public int[] solution(int brown, int yellow) {
        //brown과 yellow의 합을 구한 후 약수를 찾아서 카펫의 가로 세로를 구함.
        int total = brown+yellow;

        // 노란색은 가운데로 가야하고, 주변에 갈색이 둘러쌓아야 함으로 세로는 최소 3임
        for(int height = 3; height<=total; height++){
            if(total % height ==0){
                int width = total/height;
        // 가로는 세로보다 크거나 같아야함.
                if(width >= height){
        // 갈색의 개수는 테두리의 개수와 같아야 함.
        // 가로 *2 세로 *2를 한 뒤 중복되는 테두리 4개 빼면 갈색 타일의 개수가 나옴.
                    int calculatedBrown = (width * 2) + (height*2) -4;
                    if(calculatedBrown ==brown){
                        return new int[]{width, height};
                    }
                }
            }
        }

        return new int []{};
    }
}