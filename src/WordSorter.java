import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordSorter {
    public static void main(String[] args) {
        List<String> stringList = new LinkedList<>();
        String fileStr;
        String regex = "[ %#%&|/\t\n()+\\-,;!?{}.:\"']";
        try (BufferedReader buffReader = new BufferedReader(new FileReader("text.txt"))) {
            while ((fileStr = buffReader.readLine()) != null) {
                stringList.addAll(Arrays.asList(fileStr.trim().split(regex)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        String vowels = "aeiouy";
        for(int i=0; i <stringList.size(); i++){
            if(stringList.get(i).length() > 30)
                stringList.set(i, stringList.get(i).substring(0,30));
        }
        Comparator<String> persentVowels = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(getPercentChars(o1, vowels), getPercentChars(o2, vowels));
            }
        };
        Collections.sort(stringList, persentVowels);
        Set<String> temp = new LinkedHashSet<>(stringList);
        List<String> result_list = new LinkedList<>(temp);
        for(int i=0; i<result_list.size(); i++){
            System.out.println(result_list.get(i));}
        //System.out.println(result_list);

    }

    static int getPercentChars(String o1, String chars) {
        int count = 0;
        for (char tmpCh : o1.toCharArray())
            if (chars.contains("" + tmpCh)) count++;
        return (int)(100. * count/o1.length());
    }
}

