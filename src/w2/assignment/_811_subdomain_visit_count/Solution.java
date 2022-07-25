package w2.assignment._811_subdomain_visit_count;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> stat = new TreeMap<>();

        for (String cpdomain: cpdomains) {
            String[] s1 = cpdomain.split(" ");
            int count = Integer.parseInt(s1[0]);
            String domain = s1[1];

            List<String> subdomains = getSubDomains(domain);
            for (String subdomain: subdomains) {
                int originalCount = stat.getOrDefault(subdomain, 0);
                stat.put(subdomain, originalCount + count);
            }
        }

        return stat.entrySet().stream().map(e -> String.format("%d %s", e.getValue(), e.getKey())).collect(Collectors.toList());
    }

    private List<String> getSubDomains(String domain) {
        List<String> ans = new LinkedList<>();

        int dotIdx;
        do {
            ans.add(domain);
            dotIdx = domain.indexOf('.');
            domain = domain.substring(dotIdx + 1);
        } while(dotIdx != -1);

        return ans;
    }
}