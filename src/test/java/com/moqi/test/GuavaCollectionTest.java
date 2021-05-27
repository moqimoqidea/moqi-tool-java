package com.moqi.test;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;

import java.util.Set;

public class GuavaCollectionTest {

    public static void main(String[] args) {
        Set<String> set1 = Sets.newHashSet("1", "2");
        Set<String> set2 = Sets.newHashSet("3", "2");

        Sets.SetView<String> union = Sets.union(set1, set2);
        System.out.println("union = " + union);

        Set<String> emptySet = Sets.newHashSet();
        String join = Joiner.on(",").join(emptySet);
        System.out.println("join = " + join);
    }

}
