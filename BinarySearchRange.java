package brandon_soncarty_00874311_cscd300_prog1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearchRange {
    public static void main(String[] args) throws FileNotFoundException {

        //Scanner to read file
        Scanner input = new Scanner(new File(args[0]));

        //variables to hold low and high values
        int x = Integer.valueOf(args[1]);
        int y = Integer.valueOf(args[2]);

        //ArrayList to hold the values from the file
        List<Integer> arrList = new ArrayList<>();

        //scan the file until all values are in arrList
        while (input.hasNextLine()){
            arrList.add(input.nextInt());
        }

        //create array A to use in the methods
        int[] A = new int[arrList.size()];

        //put the values from arrList into A
        for(int i = 0; i < A.length; i++){
            A[i] = arrList.get(i);
        }

        //create a BinarySearchRange instance
        BinarySearchRange test = new BinarySearchRange();
        //Print the results of the program to the user
        System.out.println(test.binary_search_range(A,x,y));

    }

    //calls the methods I created and performs the checks given in the instructions
    public String binary_search_range(int[] A, int x, int y){

        //if the array is empty or the low value is greater than high value
        if(A.length == 0 || x > y) {
            return null;
        //if the high value is less than the value of index 0 in the array
        }else if(y < A[0]){
            return null;
        //if the low value is greater than the value of the highest index in the array
        }else if(x > A[A.length - 1]){
            return null;
        //if the low is less than the first index value and the high value is greater than the highest
        //then return the whole array as the answer
        }else if(x <= A[0] && y >= A[A.length - 1]){
            return "A[" + 0 + ".." + (A.length - 1) + "]";
        }else{
            //set s to the result of find_leftMost method
            int s = find_leftMost(A,0, A.length-1, x);
            //set t to the result of find_rightMost method
            int t = find_rightMost(A,0, A.length -1, y);
            //check to see if s and/or t are -1, and if s is <= t
            if(s <= t && (s != -1 || t != -1)){
                //if the check is passed, return s and t formatted like the instructions to the main method
                //which will then be printed to the user
                return "A[" + s + ".." + t + "]";
            }
        }
        //return null because one of the checks above failed
        return null;
    }


    //Find the index of the leftmost array location (s) whose value is >= x
    //compare x vs. A[mid]
    //1)if A[mid] >= x, double check if A[mid - 1] < x return mid unless A[mid - 1] is out of bounds for the array
    //else high = mid - 1
    //2)A[mid] < x, low = mid + 1
    public int find_leftMost(int[] A, int low, int high, int x){

        while(low <= high){
            int mid = (low+high)/2;
            if(A[mid] >= x){
                if(mid-1 < 0 || A[mid-1] < x){
                    return mid;
                }else{
                    high = mid - 1;
                    return find_leftMost(A, low, high, x);
                }
            }else{
                low = mid + 1;
                return find_leftMost(A, low, high, x);
            }
        }
        //if the left most index couldn't be found, indicate by returning -1
        return -1;
    }

    //Find the index of the rightmost array location (t) whose value is <= y
    //compare y vs. A[mid]
    //1) A[mid] <= y, double check if A[mid + 1] > y return mid unless A[mid + 1] is out of bounds for the array
    //else low = mid + 1
    //2) A[mid] < y, high = mid + 1
    public int find_rightMost(int[] A, int low, int high, int y ){

        while(low <= high){
            int mid = (low+high)/2;
            if(A[mid] <= y){
                if(mid+1 > A.length - 1 || A[mid+1] > y){
                    return mid;
                }else{
                    low = mid + 1;
                    return find_rightMost(A, low, high, y);
                }
            }else{
                high = mid - 1;
                return find_rightMost(A, low, high, y);
            }
        }
        //if the right most index couldn't be found, indicate by returning -1
        return -1;
    }

}
