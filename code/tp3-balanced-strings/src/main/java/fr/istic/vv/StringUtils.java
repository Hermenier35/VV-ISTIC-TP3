package fr.istic.vv;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        if(str == null || str.isEmpty())
            return true;

        String symbols = "{}[]()";
        String symbolsFound = "";
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int indexSymbol = symbols.indexOf(ch);
            //vérifie si mon caractère est un symbole
            if(indexSymbol != -1) {
                //si on commence par une fermeture, sans ouverture;
                if (symbolsFound.isEmpty() && symbols.indexOf(ch) % 2 == 1)
                    return false;
                //si on a au moins une ouverture d'enregistrer et
                // que le caratere est un symbole fermant
                // mais qui ne correspond pas
                if (!symbolsFound.isEmpty() && indexSymbol % 2 == 1 &&
                        symbols.charAt(indexSymbol - 1) != symbolsFound.charAt(symbolsFound.length()-1))
                    return false;
                //si balise ouvrante on ajoute a symbolsFound
                if(indexSymbol % 2 == 0)
                    symbolsFound = symbolsFound + ch;
                //si balise fermante et match avec le symbol attendu dans symbolsFound alors on le supprime.
                if(indexSymbol % 2 == 1)
                    symbolsFound = symbolsFound.substring(0, symbolsFound.length()-1);

            }
        }
        return symbolsFound.isEmpty();
    }

}
