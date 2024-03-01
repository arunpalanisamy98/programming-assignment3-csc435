package csc435.app;

import java.util.*;

public class IndexStore {

    public IndexStore() {

    }

    public void insertIndex(HashMap<String, Integer> wordCount, String fileName) {
        GlobalIndex.globalIndex.put(fileName,wordCount);
    }

    public Set<String> lookupIndex(String word1, String word2) {
        Map<String,Integer> ans = new HashMap<>();
        //iterate global index
        for (Map.Entry<String,Map<String,Integer>> entry :GlobalIndex.globalIndex.entrySet()){
            Map<String,Integer> localIndex = entry.getValue();
            if(localIndex.containsKey(word1)&&localIndex.containsKey(word2)){
                ans.put(entry.getKey(),localIndex.get(word1)+localIndex.get(word2));
            }
        }
        //sort the ans in descending order based on the value
        List<Map.Entry<String, Integer>> list = new ArrayList<>(ans.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Set<String> response = new HashSet<>();
        //adding top 10 values to the response
        for(int i=0;i<10 && i<list.size();i++){
            String s = list.get(i).getKey();
            String key = "* Dataset-"+s.substring(0,1)+"/ "+s.substring(1,s.length());
            String value = list.get(i).getValue().toString();
            response.add(key+" "+value);
        }
        return response;
    }

    public Set<String> lookupIndex(String word) {
        Map<String,Integer> ans = new HashMap<>();
        //iterate global index
        for (Map.Entry<String,Map<String,Integer>> entry :GlobalIndex.globalIndex.entrySet()){
            Map<String,Integer> localIndex = entry.getValue();
            if(localIndex.containsKey(word)){
                ans.put(entry.getKey(),localIndex.get(word));
            }
        }

        //sort the ans in descending order based on the value
        List<Map.Entry<String, Integer>> list = new ArrayList<>(ans.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Set<String> response = new HashSet<>();
        //adding top 10 values to the response
        for(int i=0;i<10 && i<list.size();i++){
            String s = list.get(i).getKey();
            String key = "* Dataset-"+s.substring(0,1)+"/ "+s.substring(1,s.length());
            String value = list.get(i).getValue().toString();
            response.add(key+" "+value);
        }
        return response;

    }
}
