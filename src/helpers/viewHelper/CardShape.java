package helpers.viewHelper;

import helpers.ViewHelper;

public class CardShape {

    public static void print(int r, int s) {

        String shapeName = getNameOfShape(s);

        String spacedShapeName = calculatSpace(shapeName, 16);

        String rank = Integer.toString(r);
        String spacedRank = calculatSpace(rank, 16);

        ViewHelper.colorText("\u001b[47;1m----------------", "red");
        ViewHelper.colorText("\u001b[47;1m" + spacedRank, "red");
        ViewHelper.colorText("\u001b[47;1m                ", "red");
        ViewHelper.colorText("\u001b[47;1m                ", "red");
        ViewHelper.colorText("\u001b[47;1m" + spacedShapeName, "red");
        ViewHelper.colorText("\u001b[47;1m                ", "red");
        ViewHelper.colorText("\u001b[47;1m                ", "red");
        ViewHelper.colorText("\u001b[47;1m" + spacedRank, "red");
        ViewHelper.colorText("\u001b[47;1m----------------", "red");
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