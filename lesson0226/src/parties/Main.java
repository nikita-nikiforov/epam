package parties;

import parties.types.*;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        // Create the company
        PartyCompany partyCompany = new PartyCompany("Назва компанії");
        // Add different party types
        partyCompany.addPartyType(new CakePainting());
        partyCompany.addPartyType(new Trampoline());
        partyCompany.addPartyType(new Bubbles());
        partyCompany.addPartyType(new Animators());

        // Get search service
        PartyCompany.SearchService searchService = partyCompany.getSearchService();

        // Make a search query #1
        TreeSet<PartyType> result = searchService
                .priceIsHigherThan(100)
                .priceIsLowerThan(200)
                .isIndoors(false)
                .completeSearch();

        // Return only "Мильні бульбашки"
        System.out.println(result);

        // Make a search query #2
        TreeSet<PartyType> result2 = searchService
                .durationIsShortenThan(125)
                .isIndoors(true)
                .completeSearch();

        // Return "Майстер-класи з розпису пряників" and "Аніматори"
        System.out.println(result2);

        // Make a search query #3
        TreeSet<PartyType> result3 = searchService
                .isIndoors(true)
                .priceIsLowerThan(60)
                .completeSearch();

        // Return nothing
        System.out.println(result3);
    }
}