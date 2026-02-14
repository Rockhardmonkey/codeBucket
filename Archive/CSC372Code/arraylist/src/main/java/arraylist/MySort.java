package arraylist;

import java.util.Comparator;
import java.util.ArrayList;


class sortbyName implements Comparator<Main.Student> {
    @Override
    public int compare(Main.Student a, Main.Student b) {
        return a.name.compareTo(b.name);
    }
}

class sortbyRollno implements Comparator<Main.Student> {
    @Override
    public int compare(Main.Student a, Main.Student b) {
        return Integer.compare(a.rollno, b.rollno);
    }
}

class SelectionSort {
    public static void mySort(ArrayList<Main.Student> list, Comparator<Main.Student> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Main.Student temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }
}
