package helpers.viewHelper;

import helpers.ViewHelper;

public class CardShape {

    // The algorithm of priting card one beside another was inspired from khalid
    // fifel
    public static void print(Integer[][] cards) {

        StringBuilder edgesOfCards = new StringBuilder();
        StringBuilder rankOfCards = new StringBuilder();
        StringBuilder shapeNameOfCards = new StringBuilder();
        StringBuilder spaceOfCards = new StringBuilder();

        for (Integer[] card : cards) {

            String shapeName = getNameOfShape(card[1]);

            String spacedShapeName = calculatSpace(shapeName, 16);

            String rank = Integer.toString(card[0]);

            String spacedRank = calculatSpace(rank, 16);

            String color = (card[1] % 2 == 0) ? "" : "\u001b[31m";

            edgesOfCards.append(color + "\u001b[47;1m----------------\u001b[0m");
            edgesOfCards.append("    ");

            rankOfCards.append(color + "\u001b[47;1m" + spacedRank + "\u001b[0m");
            rankOfCards.append("    ");

            spaceOfCards.append(color + "\u001b[47;1m                \u001b[0m");
            spaceOfCards.append("    ");

            shapeNameOfCards.append(color + "\u001b[47;1m" + spacedShapeName + "\u001b[0m");
            shapeNameOfCards.append("    ");

        }

        System.out.println(edgesOfCards);
        System.out.println(rankOfCards);
        System.out.println(spaceOfCards);
        System.out.println(spaceOfCards);
        System.out.println(shapeNameOfCards);
        System.out.println(spaceOfCards);
        System.out.println(spaceOfCards);
        System.out.println(rankOfCards);
        System.out.println(edgesOfCards);

    }

    public static String getNameOfShape(int s) {
        String[] suitNames = { "Diamond", "Heart", "Club", "Spade" };

        if (s < 0 || s > suitNames.length) {
            return "Invalid Suit";
        }

        return suitNames[s];
    }

    public static String calculatSpace(String text, int totalWidth) {

        int textLength = text.length();
        int spaceLength = totalWidth - textLength;

        if (spaceLength <= 0) {
            return "";
        }

        int leftSpace = spaceLength / 2;
        int rightSpace = spaceLength - leftSpace;

        return " ".repeat(leftSpace) + text + " ".repeat(rightSpace);
    }
}