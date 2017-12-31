import Entities.Train;
import Railway.RailwayManagement;
import Railway.RailwayService;

public class Main {

    public static void main(String[] args) {
        RailwayManagement.addStations("Kharkiv", "Kyiv", "Odesa",
                "Lviv", "Ternopil", "Zhmerynka");

        Train odesaLviv = new Train.Builder("206Ш").addStop("Odesa",
                "18:10", "18:26").addStop("Zhmerynka",
                "00:18", "00:30").addStop("Lviv",
                "06:45","07:00").build();

        Train kharkivLviv = new Train.Builder("709П").addStop("Kharkiv",
                "14:10", "14:26").addStop("Kyiv",
                "16:18", "16:30").addStop("Zhmerynka",
                "22:48", "23:30").addStop("Lviv",
                "08:10","08:50").build();

        RailwayService.printNextTrainsOnStation("Odesa");
        System.out.println();
//        RailwayService.printTrainsFromTo("Zhmerynka", "Lviv");
        RailwayService.printNextTrainsOnStationAfter("Lviv", "06:00");
    }
}