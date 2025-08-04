package week1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class problem1 {
    public static void main(String args[]) throws Exception {
        // 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        // 테스트 케이스의 수 T를 입력받음
        int T = sc.nextInt();

        // 각 테스트 케이스에 대해 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            // 들어야 하는 총 수업의 수 n을 입력받음
            int n = sc.nextInt();
            
            // 일주일 스케줄을 저장할 배열
            int[] schedule = new int[7];
            // 수업이 있는 날의 인덱스(0:일 ~ 6:토)를 저장할 리스트
            List<Integer> classDaysIndices = new ArrayList<>();
            
            // 스케줄을 입력받고, 수업이 있는 날의 정보를 저장
            for (int i = 0; i < 7; i++) {
                schedule[i] = sc.nextInt();
                if (schedule[i] == 1) {
                    classDaysIndices.add(i);
                }
            }
            
            // 일주일간 총 수업일 수
            int classesPerWeek = classDaysIndices.size();
            
            // 최소 체류일수를 저장할 변수를 매우 큰 값으로 초기화
            long minDays = Long.MAX_VALUE;

            // 수업이 열리는 모든 요일을 시작일로 가정하여 반복
            for (int startDayIndex : classDaysIndices) {
                // (n-1)개의 수업을 듣기 위해 필요한 '꽉 찬 주'의 수
                long fullWeeks = (long)(n - 1) / classesPerWeek;
                
                // '꽉 찬 주'를 보내는 데 걸리는 일수
                long daysFromWeeks = fullWeeks * 7;
                
                // 남은 수업의 수
                long remainingClasses = n - (fullWeeks * classesPerWeek);
                
                // 남은 수업을 듣기 위해 필요한 추가 일수 계산
                long additionalDays = 0;
                long tempClassCount = 0;
                int currentDayIndex = startDayIndex;
                
                while (tempClassCount < remainingClasses) {
                    if (schedule[currentDayIndex % 7] == 1) {
                        tempClassCount++;
                    }
                    additionalDays++;
                    currentDayIndex++;
                }
                
                // 현재 시작일로부터 계산된 총 체류일수
                long totalDays = daysFromWeeks + additionalDays;
                
                // 기존 최소값보다 작으면 갱신
                minDays = Math.min(minDays, totalDays);
            }
            
            // 형식에 맞춰 결과 출력
            System.out.println("#" + test_case + " " + minDays);
        }
        
        // Scanner 리소스 종료
        sc.close();
    }
}