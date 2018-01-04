import entities.Train;
import railway.RailwayManagement;
import railway.RailwayService;

public class Main {

    public static void main(String[] args) {
        RailwayManagement.addStations("Kharkiv", "Kyiv", "Odesa", "Podilsk",
                "Lviv", "Ternopil", "Zhmerynka");

        Train odesaLviv = new Train.Builder("206Ш", 25)
                .addStop("Odesa", "18:10", "18:26")
                .addStop("Podilsk", "20:57", "21:03")
                .addStop("Zhmerynka", "00:18", "00:30")
                .addStop("Lviv", "06:45","07:00")
                .build();

        Train kharkivLviv = new Train.Builder("709П", 25)
                .addStop("Kharkiv", "14:10", "14:26")
                .addStop("Kyiv", "16:18", "16:30")
                .addStop("Zhmerynka", "22:48", "23:30")
                .addStop("Lviv", "08:10","08:50")
                .build();

//        RailwayService.printAllNextTrainsOnStation("Odesa");
        System.out.println();
        RailwayService.printTrainsFromTo("Zhmerynka", "Lviv");
//        RailwayService.printNextTrainsOnStationWithinSixHoursAfter("Zhmerynka", "22:00");

        RailwayService.buyTicket("Nikita Nikiforov", "709П", 7,
                "Kharkiv", "Kyiv");
        RailwayService.buyTicket("Nikita Nikiforov", "709П", 7,
                "Kyiv", "Zhmerynka");
        RailwayService.buyTicket("Nikita Nikiforov", "709П", 7,
                "Kyiv", "Lviv");
        RailwayService.buyTicket("Nikita Nikiforov", "206Ш", 7,
                "Zhmerynka", "Lviv");
        RailwayService.buyTicket("Nikita Nikiforov", "206Ш", 7,
                "Odesa", "Podilsk");

    }
}