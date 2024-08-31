package sort;

import tripods.Tripod;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Perform an "out of place" quick sort on an array list of Tripod's by
 * ascending tripod sum.
 * <pre>
 * quick_sort (not in place):
 * best=O(nlogn)
 * worst=O(n^2)
 * </pre>
 *
 * @author RIT CS
 * @author Huy Le
 */
public class QuickSort {
    /**
     * Partition the array for values less than the pivot.
     *
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data less than the pivot
     */
    public static ArrayList<Tripod> partitionLess(ArrayList<Tripod> data, Tripod pivot) {   
        ArrayList<Tripod>less=new ArrayList<>();
            for(Tripod tripod:data){
            if(tripod.sum()<pivot.sum()){
            less.add(tripod);
            }
            }
            return less;
            
    }

    /**
     * Partition the array for values equal to the pivot.
     *
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data equal to the pivot
     */
    public static ArrayList<Tripod> partitionEqual(ArrayList<Tripod> data, Tripod pivot) {
        ArrayList<Tripod>equal=new ArrayList<>();
            for(Tripod tripod:data){
            if(tripod.sum()==pivot.sum()){
            equal.add(tripod);
            }
            }
            return equal;
    }

    /**
     * Partition the array for values greater than the pivot.
     *
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data greater than  the pivot
     */
    public static ArrayList<Tripod> partitionGreater(ArrayList<Tripod> data, Tripod pivot) {
        ArrayList<Tripod>greater=new ArrayList<>();
            for(Tripod tripod:data){
            if(tripod.sum()>pivot.sum()){
            greater.add(tripod);
            }
            }
            return greater;
    }

    /**
     * Performs a quick sort and returns a newly sorted array.
     *
     * @param data the data to be sorted
     * @return a sorted array
     */
    public static ArrayList<Tripod> quickSort(ArrayList<Tripod> data){
        if(data.size()<=1){return data;}
        Tripod pivot=data.get(0);
        ArrayList<Tripod>less=partitionLess(data,pivot);
        ArrayList<Tripod>equal=partitionEqual(data,pivot);
        ArrayList<Tripod>more=partitionGreater(data,pivot);
        ArrayList<Tripod>sorted_less=quickSort(less);
        ArrayList<Tripod>sorted_more=quickSort(more);
        sorted_less.addAll(equal);
        sorted_less.addAll(sorted_more);
        return sorted_less;
    }
}
