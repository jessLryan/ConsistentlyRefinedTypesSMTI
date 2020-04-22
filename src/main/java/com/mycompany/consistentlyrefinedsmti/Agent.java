package com.mycompany.consistentlyrefinedsmti;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jessica
 */
public class Agent implements Comparable<Agent> {
    //agent number is arbitrary (assigned according to row (for women) or 
    //column (for men) in the score table
    private int number;
    private List<List<Integer>> prefList;

    public Agent(int no) {
        number = no;
    }

    public int getNumber() {
        return number;
    }

    //each list in the preference list contains agents in a tie
    public void setPrefList(List<List<Integer>> list) {
        prefList = list;
    }

    public List<List<Integer>> getPrefList() {
        return prefList;
    }

    //agents are compared by preference list - agents who prefer lower-numbered
    //partners are placed higher in the list (we want to be able to find agents
    //with identical preference lists)
    @Override
    public int compareTo(Agent a2) {
        List<List<Integer>> prefList2 = a2.getPrefList();
        int prefListLength = prefList.size();
        int h2prefListLength = prefList2.size();
        iLoop:
        for (int i = 0;; i++) {
            if (i == prefListLength && h2prefListLength > i) {
                return -1;
            }
            if (i == h2prefListLength && prefListLength > i) {
                return 1;
            }
            if (i == h2prefListLength && i == prefListLength) {
                return 0;
            }
            for (int p = 0;; p++) {
                prefList.get(i).sort(Comparator.comparing(Integer::valueOf));
                prefList2.get(i).sort(Comparator.comparing(Integer::valueOf));
                if (p == prefList.get(i).size() && prefList2.get(i).size() > p) {
                    return 1;
                }
                if (p == prefList2.get(i).size() && prefList.get(i).size() > p) {
                    return -1;
                }
                if (p == prefList2.get(i).size() && p == prefList.get(i).size()) {
                    continue iLoop;
                }
                if (prefList.get(i).get(p) > prefList2.get(i).get(p)) {
                    return 1;
                }
                if (prefList2.get(i).get(p) > prefList.get(i).get(p)) {
                    return -1;
                }
            }

        }

    }

    
    //checks whether two agents of the opposite gender are considered equal
    //partners to this agent (i.e. in a tie)
    public boolean areAgentsRankedIdentically(Agent r1, Agent r2) {
        for (List<Integer> tie : prefList) {
            int posAgent1 = tie.indexOf(r1.getNumber());
            int posAgent2 = tie.indexOf(r2.getNumber());
            if (posAgent1 >= 0 && posAgent2 >= 0) {
                return true;
            }
        }
        return false;
    }
    
    
    public TreeMap<Integer,Agent> getPositionsOfCandidates(ArrayList<Agent> group) {
        TreeMap<Integer, Agent> positions = new TreeMap<Integer, Agent>();
        for (Agent a : group) {
            for (int i=0;i<prefList.size();i++) {
                List<Integer> tie = prefList.get(i);
                if (tie.contains(a.getNumber())) {
                    positions.put(i, a);
                }
            }
        }
        return positions;
    }
}
