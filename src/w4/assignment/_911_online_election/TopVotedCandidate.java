package w4.assignment._911_online_election;

import java.util.HashMap;
import java.util.Map;

public class TopVotedCandidate {
    int[] persons;
    int[] times;
    int[] vote;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        vote = new int[persons.length];

        buildVote();

        // System.out.println(Arrays.toString(vote));
    }

    public int q(int t) {
        int l = 0, r = persons.length - 1, m;

        while (l < r) {
            m = (l + r + 1) / 2;

            // System.out.println(String.format("%d %d %d %d", l, r, m, t));

            if (t >= times[m]) {
                l = m;
            } else {
                r = m - 1;
            }
        }

        return vote[r];
    }

    private void buildVote() {
        Map<Integer, Integer> votemap = new HashMap<>();
        int maxPerson = -1;
        int maxVote = -1;

        for (int i = 0; i < persons.length; i++) {
            int person = persons[i];
            int voteCount = votemap.getOrDefault(person, 0);
            voteCount++;

            if(voteCount >= maxVote) {
                vote[i] = person;
                maxPerson = person;
                maxVote = voteCount;
            } else {
                vote[i] = maxPerson;
            }

            votemap.put(person, voteCount);
        }
    }
}
