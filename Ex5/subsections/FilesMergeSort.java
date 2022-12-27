package subsections;

import java.io.*;
import java.util.*;

/**
 * A singleton class which sorts files given in an array list, using Merge sort.
 * @author Oren Motiei
 */
public class FilesMergeSort implements Order {

    private static FilesMergeSort single = new FilesMergeSort();
    private FilesMergeSort() {}


    /**
     * @return the instance of the singleton which is able to sort.
     */
    public static FilesMergeSort getInstance() {
        return single;
    }


    /**
     * Sorts the files in the given list according to the comparator's behavior.
     * @param list a list of files.
     * @param c a comparator to determine how the files will be sorted.
     */
    public void sort(ArrayList<File> list, Comparator<File> c) {
        int size = list.size();
        if (size <= 1)
            return;
        int mid = size / 2;
        ArrayList<File> leftList = new ArrayList<>();
        ArrayList<File> rightList = new ArrayList<>();
        for (int i=0; i<mid; i++)
            leftList.add(list.get(i));
        for (int i=mid; i<size; i++)
            rightList.add(list.get(i));
        sort(leftList, c);
        sort(rightList, c);
        merge(leftList, rightList, list, c);
    }


    /*
     * Merges two sorted sublists into a new sorted sublist.
     * @param leftList: the first half of the given list.
     * @param rightList: the second half of the given list.
     * @param list: a list of files.
     * @param c: a comparator to determine how the files will be sorted.
     */
    private void merge(ArrayList<File> leftList, ArrayList<File> rightList, ArrayList<File> list,
                       Comparator<File> c) {
        int l = 0, r = 0, n = 0;
        while (n < list.size()) {
            if (l < leftList.size() &&
                    (r >= rightList.size() || c.compare(leftList.get(l), rightList.get(r)) < 0)) {
                list.set(n, leftList.get(l));
                l++;
            } else {
                list.set(n, rightList.get(r));
                r++;
            }
            n++;
        }
    }

}
