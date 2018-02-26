package parties;

import parties.types.PartyType;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PartyCompany {
    private String name;
    // TreeSet of party types. TreeSet because we filter using compareTo!
    private TreeSet<PartyType> partyTypes;

    public PartyCompany(String name) {
        this.partyTypes = new TreeSet<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeSet<PartyType> getPartyTypes() {
        return partyTypes;
    }

    public void setPartyTypes(TreeSet<PartyType> partyTypes) {
        this.partyTypes = partyTypes;
    }

    public void addPartyType(PartyType partyType) {
        partyTypes.add(partyType);
    }

    // Get the service for making search queries
    public SearchService getSearchService(){
        return new SearchService();
    }

    // Class for building search queries
    public class SearchService{
        // TreeSet which will be having filtered by chaining queries
        private TreeSet<PartyType> partyTypes;

        public SearchService() {
            this.partyTypes = PartyCompany.this.partyTypes;
        }

        public SearchService isIndoors(boolean value){
            partyTypes = partyTypes
                    .stream()
                    .filter(partyType -> partyType.isIndoors() == value)
                    .collect(Collectors.toCollection(TreeSet::new));
            return this;
        }

        public SearchService priceIsLowerThan(int value){
            partyTypes = partyTypes
                    .stream()
                    .filter(partyType -> partyType.getPrice() < value)
                    .collect(Collectors.toCollection(TreeSet::new));
            return this;
        }

        public SearchService priceIsHigherThan(int value) {
            partyTypes = partyTypes
                    .stream()
                    .filter(partyType -> partyType.getPrice() > value)
                    .collect(Collectors.toCollection(TreeSet::new));
            return this;
        }

        public SearchService durationIsShortenThan(int value){
            partyTypes = partyTypes
                    .stream()
                    .filter(partyType -> partyType.getDuration() < value)
                    .collect(Collectors.toCollection(TreeSet::new));
            return this;
        }

        public SearchService durationIsLongerThan(int value){
            partyTypes = partyTypes
                    .stream()
                    .filter(partyType -> partyType.getDuration() > value)
                    .collect(Collectors.toCollection(TreeSet::new));
            return this;
        }

        // Final method of every search query
        public TreeSet<PartyType> completeSearch(){
            TreeSet<PartyType> result = partyTypes;
            // Get back the initial state of partyTypes in SearchService
            partyTypes = PartyCompany.this.partyTypes;
            return result;
        }
    }
}