package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Node;

public class WikiPhilosophy {

    final static List<String> visited = new ArrayList<>();
    final static WikiFetcher wf = new WikiFetcher();

    final static String prefix = "https://en.wikipedia.org";

    /**
     * Tests a conjecture about Wikipedia and Philosophy.
     *
     * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
     *
     * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
     *
     */
    public static void main(String[] args) throws IOException {
        String destination = "https://en.wikipedia.org/wiki/Philosophy";
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";

        testConjecture(destination, source, 10);
    }

    public static void testConjecture(String destination, String source, int limit) throws IOException {
        Elements paras = wf.fetchWikipedia(source);

        for (Element para : paras) {
            if (para.hasText()) {
                Iterable<Node> iter = new WikiNodeIterable(para);
                for (Node node : iter) {

//                    if (!node.nodeName().equals("a")) {
//                        continue;
//                    }
                    System.out.println(node.nodeName() + " : " + node);
                }
            }
        }
    }
}
