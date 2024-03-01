package csc435.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GlobalIndex {
    /*
    map format
    <databaseNumber+filename<word,count>>
     */
    static Map<String, Map<String,Integer>> globalIndex = new HashMap<>();
    static Set<String> connectedClients = new HashSet<>();
}
